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
                wrapEntityWithDates($scope.user, ["birthDate"]);
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

        $('#resultImageContainer').html(croppedCanvas);
    };

    $scope.onFormSubmit = function () {
        var fd = new FormData();
        fd.append('file', $scope.imageBlob);
        fd.append('user', angular.toJson($scope.user));

        $http.post('api/users', fd, {
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
