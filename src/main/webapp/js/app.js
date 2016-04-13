var app = angular.module('userApp', ['ui.router']);

app.run([
    '$rootScope', '$state', '$stateParams', '$http', 'authService',
    function ($rootScope, $state, $stateParams, $http, authService) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;

        $rootScope.user = $http
            .get('api/users/currentUser')
            .then(function (response) {
                $rootScope.loggedUser = response.data;
                $rootScope.loggedUserRoles = $rootScope.loggedUser.roles;
                $rootScope.authService = authService;

                $rootScope.$on('$stateChangeStart',
                    function (event, toState, toParams, fromState, fromParams) {
                        if (!authService.checkAccess(event, toState, toParams, fromState, fromParams)) {
                            event.preventDefault();
                            $state.go('home');
                        }
                    }
                );
            });

    }
]);

app.service('authService', function ($rootScope) {
    this.checkAccess = function (event, toState, toParams, fromState, fromParams) {
        if (toState.data !== undefined && toState.data.permitTo) {
            var permitTo = toState.data.permitTo;
            var currentRoles = $rootScope.loggedUserRoles;

            return _checkIntersection(currentRoles, permitTo);
        } else {
            return true;
        }
    };

    this.checkRights = function (permitTo) {
        var currentRoles = $rootScope.loggedUserRoles;
        if (permitTo) {
            return _checkIntersection(currentRoles, permitTo);
        }
    };

    _checkIntersection = function (currentRoles, permitTo) {
        var permitAccess = false;

        for (var i = 0; i < permitTo.length; i++) {
            for (var j = 0; j < currentRoles.length; j++) {
                if (permitTo[i] === currentRoles[j].roleName) {
                    return true;
                }
            }
        }
        return permitAccess;
    }
});
