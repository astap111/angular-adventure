var app = angular.module('userApp', ['ui.router']);

app.config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('main', {
            url: '/'
        })

        .state('users', {
            url: '/users?page&pageSize',
            templateUrl: 'partials/users.html',
            controller: usersController,
            params: {
                page: '0',
                pageSize: '5'
            }
        })

        .state('userDetails', {
            url: '/users/{userId:int}',
            templateUrl: 'partials/userDetails.html',
            controller: userDetailsController
        })

        .state('addUser', {
            url: '/users/add',
            templateUrl: 'partials/userDetails.html',
            controller: addUserController
        });
});

var usersController = function ($scope, $http, $stateParams, $state) {
    var self = this;
    self.page = parseInt($stateParams.page, 10);
    self.pageSize = parseInt($stateParams.pageSize, 10);

    updatePage();

    function updatePage() {
        $http({
            url: 'api/users',
            method: 'GET',
            params: {page: self.page, pageSize: self.pageSize}
        }).then(function (response) {
            $scope.pageCtx = response.data;
            $scope.users = response.data.content;
            $scope.pager = {pages: []};
            for (var i = 0; i < $scope.pageCtx.totalPages; i++) {
                $scope.pager.pages.push(i);
            }
        });
    }
};

var userDetailsController = function ($scope, $http, $stateParams, $state) {
    $scope.availableRoles = getUserRoles();
    $scope.availableStatuses = getStatuses();
    $scope.user = {status: 'ACTIVE'};

    $http.get('api/users/' + $stateParams.userId)
        .then(function (response) {
            $scope.user = response.data;
            wrapUserDate($scope.user);
        });

    $scope.onFormSubmit = function () {
        $http.post('api/users', $scope.user)
            .then(
            function (response) {
                $state.go('users');
            }
        );
    }
};

var addUserController = function ($scope, $http, $state) {
    $scope.availableRoles = getUserRoles();
    $scope.availableStatuses = getStatuses();
    $scope.user = {status: 'ACTIVE'};

    $scope.onFormSubmit = function () {
        $http.put('api/users', $scope.user)
            .then(
            function (response) {
                $state.go('users');
            }
        );
    }
};


function wrapUserDate(user) {
    user.birthDate = new Date(user.birthDate);
}

function getUserRoles() {
    return [{roleName: 'ROLE_SYSTEM_ADMIN'}, {roleName: 'ROLE_ADMIN'}, {roleName: 'ROLE_DISPATCHER'}, {roleName: 'ROLE_MANAGER'}, {roleName: 'ROLE_CONTROLLER'}, {roleName: 'ROLE_OWNER'}];
}

function getStatuses() {
    return ['ACTIVE', 'BLOCKED'];
}