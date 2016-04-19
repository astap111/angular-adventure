var profitAndLossController = function ($scope, $http, $stateParams) {
    $http({
        url: 'api/reports/profitAndLoss',
        method: 'GET'
    }).then(function (response) {
        report = {dates: [], profits: [], losses: [], totals: []};

        var profitAndLosses = response.data;

        profitAndLosses.forEach(function (item) {
            report.profits.push([item.date, item.profit]);
            report.losses.push([item.date, item.loss]);
            report.totals.push([item.date, item.profit - item.loss]);
            initChart();
        });


        function initChart() {
            $('#container').highcharts({
                chart: {
                    type: 'line'
                },
                title: {
                    text: 'Profit & Loss Chart'
                },
                subtitle: {
                    text: 'Your company'
                },
                xAxis: {
                    type: 'datetime',
                    dateTimeLabelFormats: { // don't display the dummy year
                        month: '%e. %b',
                        year: '%b'
                    },
                    title: {
                        text: 'Date'
                    }
                },
                yAxis: [{ //--- Primary yAxis
                    title: {
                        text: '$'
                    }
                }, { //--- Secondary yAxis
                    title: {
                        text: '$'
                    },
                    opposite: true
                }],
                plotOptions: {
                    line: {
                        dataLabels: {
                            enabled: true
                        },
                        enableMouseTracking: true
                    }
                },
                series: [{
                    yAxis: 0,
                    name: 'Profit',
                    data: report.profits
                }, {
                    yAxis: 1,
                    name: 'Loss',
                    data: report.losses
                }, {
                    yAxis: 0,
                    name: 'Total',
                    data: report.totals
                }]
            });
        }
    });
};
