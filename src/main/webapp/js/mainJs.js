var app = angular.module('userApp', ['ui.router']);

app.config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('users', {
            url: "/users",
            templateUrl: "partials/users.html",
            controller: usersController
        })

        .state('userDetails', {
            url: "/users/{userId}:{0-9}",
            templateUrl: "partials/userDetails.html",
            controller: userDetailsController
        })

        .state('addUser', {
            url: "/users/add",
            templateUrl: "partials/userDetails.html",
            controller: addUserController
        });
});

var usersController = function ($scope, $http) {
    $http.get("api/user")
        .then(function (response) {
            $scope.users = response.data;
        });
};

var userDetailsController = function ($scope, $http, $stateParams, $state) {
    $scope.roles = [{roleName: 'ROLE_USER'}, {roleName: 'ROLE_ADMIN'}];

    $http.get("api/user/" + $stateParams.userId)
        .then(function (response) {
            $scope.user = response.data;
        });

    $scope.onFormSubmit = function () {
        $http.post("api/user", $scope.user)
            .then(
                function (response) {
                    $state.go('users');
                }
            );
    }
};

var addUserController = function ($scope, $http, $state) {
    $scope.user = {roles: [{roleName: null}]};

    $scope.roles = [{roleName: 'one'}, {roleName: 'two'}, {roleName: 'three'}];

    $scope.onFormSubmit = function () {
        $http.put("api/user", $scope.user)
            .then(
                function (response) {
                    $state.go('users');
                }
            );
    }
};