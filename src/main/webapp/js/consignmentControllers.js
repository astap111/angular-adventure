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


var addConsignmentController = function ($scope, $http, $state, client) {
    $scope.consignment = {senderCompany: {name: ""}};

    $scope.$watch('consignment.senderCompany.name', function (newValue, oldValue) {
        if (newValue) {
            //check if newValue is exact name of a sender
            if (oldValue && newValue.indexOf(oldValue) > -1) {
                $scope.senders.forEach(function (sender) {
                    if (sender.name === newValue) {
                        chooseSender(sender.id);
                        return;
                    }
                });
            } else {
                $scope.senders = [];

                searchInElastic(newValue, function (response) {
                    response.hits.hits.forEach(function (hit) {
                        $scope.senders.push(hit._source)
                    })
                });
            }
        } else {
            $scope.senders = [];
        }
    });

    function chooseSender(id) {
        $http({
            url: 'api/companies/' + id,
            method: 'GET'
        }).then(function (response) {
            $scope.consignment.senderCompany = response.data;
        });
    }

    function searchInElastic(name, callback) {
        client.search({
            index: 'warehouse',
            type: 'SENDER_COMPANY',
            q: "name:" + name + "*"
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
