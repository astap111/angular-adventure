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
