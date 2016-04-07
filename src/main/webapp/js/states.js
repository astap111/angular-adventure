$stateProvider.state('users', {
    templateUrl: 'index.jsp'
});

$stateProvider.state('addUser', {
    templateUrl: 'editUser.html',
    controller: userCtrl
});

// $stateProvider.state('editUser', {
//     templateUrl: 'editUser.html',
//     controller: 'userCtrl'
// });


app.controller('userCtrl', function ($scope, $http) {
    $http.get("api/user")
        .then(function (response) {
            $scope.users = response.data;

        });
    $scope.addUser = function () {
        $http.post('api/user', $scope.userToAdd, config).then(successCallback, errorCallback);
    }
});