var companiesController = function ($scope, $http, $stateParams) {
    var self = this;
    self.companyType = $stateParams.companyType;

    //LAZY LOADING OR NOT
    if (companyType = 'WAREHOUSE_COMPANY') {
        self.lazy = false;
        self.page = 0;
        self.pageSize = 0;
    } else {
        self.lazy = true;
        self.page = parseInt($stateParams.page, 10);
        self.pageSize = parseInt($stateParams.pageSize, 10);
    }

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

            var points = [];
            for (var i = 0; i < $scope.companies.length; i++) {
                points.push({
                    name: $scope.companies[i].name,
                    lat: $scope.companies[i].latitude,
                    lon: $scope.companies[i].longitude
                });
            }
            loadMap(points);

            if (self.lazy) {
                $scope.pager = {pages: []};
                if ($scope.pageCtx.totalPages < 7) {
                    for (i = 0; i < $scope.pageCtx.totalPages; i++) {
                        $scope.pager.pages.push(i);
                    }
                } else {
                    var start = $scope.pageCtx.number > 2 ? $scope.pageCtx.number - 3 : 0;
                    var end = $scope.pageCtx.number < $scope.pageCtx.totalPages - 3 ? $scope.pageCtx.number + 3 : $scope.pageCtx.totalPages;
                    for (i = start; i < end; i++) {
                        $scope.pager.pages.push(i);
                    }
                }
            } else {
                $scope.pageCtx.number = 0;
                $scope.pageCtx.numberOfElements = 5;
                $scope.pageCtx.totalPages = Math.ceil($scope.pageCtx.totalElements / $scope.pageCtx.numberOfElements);

                $scope.pager = {pages: []};
                if ($scope.pageCtx.totalPages < 7) {
                    var start = $scope.pageCtx.number > 2 ? $scope.pageCtx.number - 3 : 0;
                    var end = $scope.pageCtx.number < $scope.pageCtx.totalPages - 3 ? $scope.pageCtx.number + 3 : $scope.pageCtx.totalPages;
                    for (i = start; i < end; i++) {
                        $scope.pager.pages.push(i);
                    }
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

            function updateMap() {
                var point = [{
                    name: $scope.company.name,
                    lat: $scope.company.latitude,
                    lon: $scope.company.longitude
                }];
                loadMap(point);
            }

            updateMap();

            $scope.$watch('out', function (newOut) {
                if (newOut) {
                    if (!$scope.company) {
                        $scope.company = {};
                    }
                    $scope.company.latitude = newOut.geometry.location.lat();
                    $scope.company.longitude = newOut.geometry.location.lng();
                    updateMap();
                }
            });
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

    $scope.my_place_id = "ChIJ02oeW9PP20YR2XC13VO4YQs";

    $scope.$watch('out', function (newOut) {
        if (newOut) {
            if (!$scope.company) {
                $scope.company = {};
            }
            $scope.company.latitude = newOut.geometry.location.lat();
            $scope.company.longitude = newOut.geometry.location.lng();
            var point = [{
                name: $scope.company.name,
                lat: $scope.company.latitude,
                lon: $scope.company.longitude
            }];
            loadMap(point);
        }
    });

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


function loadMap(points) {
    $(function () {

        $('#container').highcharts('Map', {

            title: {
                text: 'Warehouses'
            },

            subtitle: {
                text: 'Source map: <a href="https://code.highcharts.com/mapdata/countries/by/by-all.js">Belarus</a>'
            },

            mapNavigation: {
                enabled: true
            },

            tooltip: {
                headerFormat: '',
                pointFormat: '<b>{point.name}</b><br>Lat: {point.lat}, Lon: {point.lon}'
            },

            series: [{
                mapData: Highcharts.maps['countries/by/by-all'],
                name: 'Warehouses',
                borderColor: '#A0A0A0',
                nullColor: 'rgba(200, 200, 200, 0.3)',
                showInLegend: false
            }, {
                name: 'Separators',
                type: 'mapline',
                data: Highcharts.geojson(Highcharts.maps['countries/by/by-all'], 'mapline'),
                color: '#707070',
                showInLegend: false,
                enableMouseTracking: false
            }, {
                type: 'mappoint',
                name: 'Warehouses',
                color: Highcharts.getOptions().colors[1],
                data: points
            }]
        });
    });
}