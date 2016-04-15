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

var userDetailsController = function ($scope, $http, $stateParams, $state, FileUploader) {
    init();

    function init() {
        $scope.availableRoles = getUserRoles();
        $scope.availableStatuses = getStatuses();
        $scope.user = {status: 'ACTIVE'};

        $http.get('api/users/' + $stateParams.userId)
            .then(function (response) {
                $scope.user = response.data;
                wrapUserDate($scope.user);
            });
    }

    // File upload
    var uploader = $scope.uploader = new FileUploader();

    //ImageCropper
    $scope.previewPhoto = function () {
        //Check File API support
        if (window.File && window.FileList && window.FileReader) {
            var filesInput = document.getElementById("file");
            var file = document.querySelector('input[type=file]').files[0];
            var pictureForCrop = document.getElementById("pictureForCrop");

            //Only pics
            if (!file.type.match('image')) return;

            var picReader = new FileReader();

            picReader.addEventListener("load", function (event) {
                var picFile = event.target;
                pictureForCrop.innerHTML = "<img id='previewPhoto' class='thumbnail' src='" + picFile.result + "'" +
                    "title='" + picFile.name + "'/>";

                $scope.$image = $('#previewPhoto').cropper({
                    aspectRatio: 3 / 4,
                    modal: true,
                    crop: function (e) {
                        // Output the result data for cropping image.
                        console.log(e.x);
                        console.log(e.y);
                        console.log(e.width);
                        console.log(e.height);
                        console.log(e.rotate);
                        console.log(e.scaleX);
                        console.log(e.scaleY);
                    }
                });
            });

            //Read the image
            picReader.readAsDataURL(file);
        }
        else {
            console.log("Your browser does not support File API");
        }
    };

    $scope.setResultToCanvas = function () {
        var croppedCanvas = $scope.$image.cropper('getCroppedCanvas');

        croppedCanvas.toBlob(function (blob) {
            $scope.imageBlob = blob;
        });

        $('#resultImage').html(croppedCanvas);
    };

    $scope.onFormSubmit = function () {
        var fd = new FormData();
        fd.append('file', $scope.imageBlob);
        fd.append('user', angular.toJson($scope.user));

        $http.post('api/fileUpload', fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })
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

// app.controller('FileUploadController', ['$rootScope', '$scope', 'FileUploader', function ($rootScope, $scope, FileUploader) {
//     var uploader = $scope.uploader = new FileUploader({
//         url: 'api/fileUpload',
//         tagret: 'user' + 1
//     });
// }]);

