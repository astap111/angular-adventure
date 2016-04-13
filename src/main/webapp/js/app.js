var app = angular.module('userApp', ['ui.router']);

app.run([
    '$rootScope', '$state', '$stateParams', '$http', 'authServise',
    function ($rootScope, $state, $stateParams, $http, authServise) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;

        $rootScope.user = $http
            .get('api/users/currentUser')
            .then(function (response) {
                $rootScope.loggedUser = response.data;
                $rootScope.loggedUserRoles = $rootScope.loggedUser.roles;

                $rootScope.$on('$stateChangeStart',
                    function (event, toState, toParams, fromState, fromParams) {
                        if (!authServise.checkAccess(event, toState, toParams, fromState, fromParams)) {
                            event.preventDefault();
                            $state.go('home');
                        }
                    }
                );
            });
    }
]);

app.service('authServise', function ($rootScope) {
    this.checkAccess = function (event, toState, toParams, fromState, fromParams) {
        if (toState.data !== undefined && toState.data.permitTo) {
            var permitTo = toState.data.permitTo;
            var currentRoles = $rootScope.loggedUserRoles;
            var permitAccess = false;

            for (var i = 0; i < permitTo.length; i++) {
                for (var j = 0; j < currentRoles.length; j++) {
                    if (permitTo[i] === currentRoles[j].roleName) {
                        return true;
                    }
                }
            }
            return permitAccess;
        } else {
            return true;
        }
    }
});
