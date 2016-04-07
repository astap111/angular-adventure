var app = angular.module('userApp', ['ui.router']);

app.config(function ($stateProvider, $urlRouterProvider) {
    //
    // For any unmatched url, redirect to /users
    // $urlRouterProvider.otherwise("/");

    $stateProvider
        .state('users', {
            url: "/users",
            templateUrl: "partials/users.jsp",
            controller: function ($scope, $http) {
                $http.get("api/user")
                    .then(function (response) {
                        $scope.users = response.data;
                    });
            }
        });

    $stateProvider
        .state('userDetails', {
            url: "/users/{userId}",
            templateUrl: "partials/userDetails.jsp",
            controller: function ($scope, $http, $stateParams, $state) {
                $http.get("api/user/" + $stateParams.userId)
                    .then(function (response) {
                        $scope.user = response.data;
                    });

                $scope.onFormSubmit = function () {
                    $http.post("api/user", $scope.user)
                        .then(
                        function (response) {
                            $state.go('users')
                        }
                    );
                }
            }
        });
});


