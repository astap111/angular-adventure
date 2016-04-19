app.config(function ($stateProvider) {
    $stateProvider
        .state('home', {
            url: '/'
        })

        .state('profitAndLoss', {
            url: '/profitAndLoss',
            templateUrl: 'partials/profitAndLoss.html',
            controller: profitAndLossController
        })


        // USERS
        .state('users', {
            url: '/users?page&pageSize',
            templateUrl: 'partials/users.html',
            controller: usersController,
            params: {
                page: '0',
                pageSize: '5'
            },
            data: {
                permitTo: ['ROLE_SYSTEM_ADMIN']
            }
        })

        .state('userDetails', {
            url: '/users/{userId:int}',
            templateUrl: 'partials/userDetails.html',
            controller: userDetailsController
        })

        .state('addUser', {
            url: '/users/add',
            templateUrl: 'partials/userDetails.html',
            controller: addUserController
        })


        // COMPANIES
        .state('companies', {
            url: '/companies?companyType&page&pageSize',
            templateUrl: 'partials/companies.html',
            controller: companiesController,
            params: {
                companyType: '',
                page: '0',
                pageSize: '5'
            }
        })

        .state('companyDetails', {
            url: '/companies/{companyId:int}',
            templateUrl: 'partials/companyDetails.html',
            controller: companyDetailsController
        })

        .state('addCompany', {
            url: '/companies/add?companyType',
            templateUrl: 'partials/companyDetails.html',
            controller: addCompanyController,
            params: {
                companyType: ''
            }
        })


        // CONSIGNMENTS
        .state('consignments', {
            url: '/consignments?page&pageSize',
            templateUrl: 'partials/consignments.html',
            controller: consignmentsController,
            params: {
                page: '0',
                pageSize: '5'
            }
        })

        .state('consignmentDetails', {
            url: '/consignments/{consignmentId:int}',
            templateUrl: 'partials/consignmentDetails.html',
            controller: consignmentDetailsController
        })

        .state('addConsignment', {
            url: '/consignments/add',
            templateUrl: 'partials/consignmentDetails.html',
            controller: addConsignmentController
        })
});
