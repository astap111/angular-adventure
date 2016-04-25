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

var consignmentDetailsController = function ($scope, $http, $stateParams, $state, client) {
    updatePage();
    $scope.senders = [];

    function updatePage() {
        $http.get('api/consignments/' + $stateParams.consignmentId)
            .then(function (response) {
                $scope.consignment = response.data;
                wrapConsignmentDate($scope.consignment);

                $scope.senders.push($scope.consignment.senderCompany);
            });
    }

    $scope.selectionChanged = function (value) {
        $http({
            url: 'api/companies/' + value.id,
            method: 'GET'
        }).then(function (response) {
            $scope.consignment.senderCompany = response.data;
        });
    };

    $scope.loadSenders = function (callback, searchString, searchFrom) {
        $scope.senders = [];
        searchInElastic(searchString, searchFrom, function (response) {
            itemsProcessed = 0;
            response.hits.hits.forEach(function (item, index, array) {
                $scope.senders.push(item._source);
                itemsProcessed++;
                if (itemsProcessed === array.length) {
                    callback($scope.senders, response.hits.total);
                }
            });
        });
    };

    function searchInElastic(searchString, searchFrom, callback) {
        client.search({
            index: 'warehouse',
            type: 'SENDER_COMPANY',
            q: "name:*" + searchString + "*",
            from: (searchFrom)
        }, function (error, response) {
            callback(response);
        });
    }

    $scope.onFormSubmit = function () {
        $http.post('api/consignments', $scope.consignment)
            .then(
                function (response) {
                    $state.go('consignments');
                }
            );
    }
};


var addConsignmentController = function ($scope, $http, $state, client) {
    $scope.consignment = {senderCompany: {name: ""}};

    $scope.selectionChanged = function (value) {
        $http({
            url: 'api/companies/' + value.id,
            method: 'GET'
        }).then(function (response) {
            $scope.consignment.senderCompany = response.data;
        });
    };

    $scope.loadSenders = function (callback, searchString, searchFrom) {
        $scope.senders = [];
        searchInElastic(searchString, searchFrom, function (response) {
            itemsProcessed = 0;
            response.hits.hits.forEach(function (item, index, array) {
                $scope.senders.push(item._source);
                itemsProcessed++;
                if (itemsProcessed === array.length) {
                    callback($scope.senders, response.hits.total);
                }
            });
        });
    };

    function searchInElastic(searchString, searchFrom, callback) {
        client.search({
            index: 'warehouse',
            type: 'SENDER_COMPANY',
            q: "name:*" + searchString + "*",
            from: (searchFrom)
        }, function (error, response) {
            callback(response);
        });
    }

    $scope.onFormSubmit = function () {
        $http.put('api/consignments', $scope.consignment)
            .then(
                function (response) {
                    $state.go('consignments');
                }
            );
    }
};
