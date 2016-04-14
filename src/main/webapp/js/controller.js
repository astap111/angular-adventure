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


// CONSIGNMENT CTRL
var consignmentsController = function ($scope, $http, $stateParams) {
    var self = this;
    self.page = parseInt($stateParams.page, 10);
    self.pageSize = parseInt($stateParams.pageSize, 10);

    updatePage();

    function updatePage() {
        $http({
            url: 'api/consignments',
            params: {page: self.page, pageSize: self.pageSize},
            method: 'GET'
        }).then(function (response) {
            $scope.pageCtx = response.data;
            $scope.consignments = response.data.content;
            $scope.pager = {pages: []};
            for (var i = 0; i < $scope.pageCtx.totalPages; i++) {
                $scope.pager.pages.push(i);
            }
        });
    }
};

var consignmentDetailsController = function ($scope, $http, $stateParams, $state) {

    $http.get('api/consignments/' + $stateParams.consignmentId)
        .then(function (response) {
            $scope.consignment = response.data;
            wrapConsignmentDate($scope.consignment);
        });

    $scope.onFormSubmit = function () {
        $http.post('api/consignments', $scope.consignment)
            .then(
                function (response) {
                    $state.go('consignments');
                }
            );
    }
};


var addConsignmentController = function ($scope, $http, $state) {
    $scope.senderCompanies = $http({
        url: 'api/companies',
        params: {companyType: 'SENDER_COMPANY'},
        method: 'GET'
    }).then(function (response) {
        $scope.pageCtx = response.data;
        $scope.companies = response.data.content;
    });

    $scope.onFormSubmit = function () {
        $http.put('api/consignments', $scope.consignment)
            .then(
                function (response) {
                    $state.go('consignments');
                }
            );
    }
};

app.controller('FileUploadController', ['$rootScope', '$scope', 'FileUploader', function ($rootScope, $scope, FileUploader) {
    var uploader = $scope.uploader = new FileUploader({
        url: 'api/fileUpload'
    });

    // FILTERS

    uploader.filters.push({
        name: 'customFilter',
        fn: function (item /*{File|FileLikeObject}*/, options) {
            return this.queue.length < 10;
        }
    });

    // CALLBACKS

    uploader.onWhenAddingFileFailed = function (item /*{File|FileLikeObject}*/, filter, options) {
        console.info('onWhenAddingFileFailed', item, filter, options);
    };
    uploader.onAfterAddingFile = function (fileItem) {
        console.info('onAfterAddingFile', fileItem);
    };
    uploader.onAfterAddingAll = function (addedFileItems) {
        console.info('onAfterAddingAll', addedFileItems);
    };
    uploader.onBeforeUploadItem = function (item) {
        console.info('onBeforeUploadItem', item);
    };
    uploader.onProgressItem = function (fileItem, progress) {
        console.info('onProgressItem', fileItem, progress);
    };
    uploader.onProgressAll = function (progress) {
        console.info('onProgressAll', progress);
    };
    uploader.onSuccessItem = function (fileItem, response, status, headers) {
        console.info('onSuccessItem', fileItem, response, status, headers);
    };
    uploader.onErrorItem = function (fileItem, response, status, headers) {
        console.info('onErrorItem', fileItem, response, status, headers);
    };
    uploader.onCancelItem = function (fileItem, response, status, headers) {
        console.info('onCancelItem', fileItem, response, status, headers);
    };
    uploader.onCompleteItem = function (fileItem, response, status, headers) {
        console.info('onCompleteItem', fileItem, response, status, headers);
    };
    uploader.onCompleteAll = function () {
        console.info('onCompleteAll');
    };

    console.info('uploader', uploader);
}]);
