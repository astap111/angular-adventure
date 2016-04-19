var companiesController = function ($scope, $http, $stateParams) {
    var self = this;
    self.page = parseInt($stateParams.page, 10);
    self.pageSize = parseInt($stateParams.pageSize, 10);
    self.companyType = $stateParams.companyType;
    $scope.companyType = self.companyType;

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
            if ($scope.pageCtx.totalPages < 7) {
                for (var i = 0; i < $scope.pageCtx.totalPages; i++) {
                    $scope.pager.pages.push(i);
                }
            } else {
                var start = $scope.pageCtx.number > 2 ? $scope.pageCtx.number - 3 : 0;
                var end = $scope.pageCtx.number < $scope.pageCtx.totalPages - 3 ? $scope.pageCtx.number + 3 : $scope.pageCtx.totalPages;
                for (var i = start; i < end; i++) {
                    $scope.pager.pages.push(i);
                }
            }
        });
    }
};

var companyDetailsController = function ($scope, $http, $stateParams, $state) {
    $http.get('api/companies/' + $stateParams.companyId)
        .then(function (response) {
            $scope.company = response.data;
            wrapEntityWithDates($scope.company, ["date"]);
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

var addCompanyController = function ($scope, $http, $state, $stateParams) {
    $scope.companyType = $stateParams.companyType;

    $scope.onFormSubmit = function () {
        $scope.company.type = $scope.companyType;
        $http.put('api/companies', $scope.company)
            .then(
                function (response) {
                    $state.go('companies', {companyType: $scope.companyType});
                }
            );
    }
};
