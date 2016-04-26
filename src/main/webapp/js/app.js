var app = angular.module('warehouseApp', ['ui.router', 'angularFileUpload', 'angular-google-maps-geocoder', 'elasticsearch', 'acute.select', 'AngularStompDK']);

app.config(function (ngstompProvider) {
    ngstompProvider
        .url('ws:/localhost:8080/stomp');
});

app.run([
    '$rootScope', '$state', '$stateParams', '$http', 'authService', 'acuteSelectService', 'ngstomp',
    function ($rootScope, $state, $stateParams, $http, authService, acuteSelectService, ngstomp) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;

        acuteSelectService.updateSetting("templatePath", "/acute.select");

        $rootScope.user = $http
            .get('api/users/currentUser')
            .then(function (response) {
                $rootScope.loggedUser = response.data;
                $rootScope.authService = authService;

                // Subscribe to STOMP
                if (authService.checkRights(['ROLE_DISPATCHER'])) {
                    var whatToDoWhenMessageComming = function (message) {
                        var alertConsignment = JSON.parse(message.body);
                        $rootScope.alert = {
                            href: "#/consignments/" + alertConsignment.id,
                            message: "New consignment #" + alertConsignment.number + " registered"
                        };
                    };
                    ngstomp
                        .subscribeTo('/topic/dispatcher')
                        .callback(whatToDoWhenMessageComming)
                        .connect();
                }

                $rootScope.$on('$stateChangeStart',
                    function (event, toState, toParams, fromState, fromParams) {
                        $rootScope.alert = null;
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
    this.checkAccess = function (event, toState) {
        if (toState.data && toState.data.permitTo) {
            var permitTo = toState.data.permitTo;
            var currentRoles = $rootScope.loggedUser.roles;

            return _checkIntersection(currentRoles, permitTo);
        } else {
            return true;
        }
    };

    this.checkRights = function (permitTo) {

        return _checkIntersection($rootScope.loggedUser.roles, permitTo);
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

app.service('client', function (esFactory) {
    return esFactory({
        host: 'localhost:9200'
    });
});