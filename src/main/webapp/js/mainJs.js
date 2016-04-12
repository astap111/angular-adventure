var app = angular.module('userApp', ['ui.router']);

app.config(function ($stateProvider) {
    $stateProvider
        .state('main', {
            url: '/'
        })


        // USERS
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
        })


        // COMPANIES
        .state('companies', {
            url: '/companies?companyType&page&pageSize',
            templateUrl: 'partials/companies.html',
            controller: companiesController,
            params: {
                companyType: '',
                page: '0',
                pageSize: '5'
            }
        })

        .state('companyDetails', {
            url: '/companies/{companyId:int}',
            templateUrl: 'partials/companyDetails.html',
            controller: companyDetailsController
        })

        .state('addCompany', {
            url: '/companies/add',
            templateUrl: 'partials/companyDetails.html',
            controller: addCompanyController
        });
});


// USER CTRL
var usersController = function ($scope, $http, $stateParams) {
    var self = this;
    self.page = parseInt($stateParams.page, 10);
    self.pageSize = parseInt($stateParams.pageSize, 10);

    updatePage();

    function updatePage() {
        $http({
            url: 'api/users',
            params: {page: self.page, pageSize: self.pageSize},
            method: 'GET'
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


// COMPANY CTRL
var companiesController = function ($scope, $http, $stateParams) {
    var self = this;
    self.page = parseInt($stateParams.page, 10);
    self.pageSize = parseInt($stateParams.pageSize, 10);
    self.companyType = $stateParams.companyType;

    updatePage();

    function updatePage() {
        $http({
            url: 'api/companies',
            params: {
                companyType: self.companyType,
                page: self.page,
                pageSize: self.pageSize
            },
            method: 'GET'
        }).then(function (response) {
            $scope.pageCtx = response.data;
            $scope.companies = response.data.content;
            $scope.pager = {pages: []};
            for (var i = 0; i < $scope.pageCtx.totalPages; i++) {
                $scope.pager.pages.push(i);
            }
        });
    }
};

var companyDetailsController = function ($scope, $http, $stateParams, $state) {
    $http.get('api/companies/' + $stateParams.companyId)
        .then(function (response) {
            $scope.company = response.data;
        });

    $scope.onFormSubmit = function () {
        $http.post('api/companies', $scope.company)
            .then(
                function (response) {
                    $state.go('companies');
                }
            );
    }
};

var addCompanyController = function ($scope, $http, $state) {
    $scope.onFormSubmit = function () {
        $http.put('api/companies', $scope.company)
            .then(
                function (response) {
                    $state.go('companies');
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