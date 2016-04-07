<div class="page-header">
    <h1>User details</h1>
</div>
<div class="row">

    <div class="col-md-12">
        <form ng-submit="onFormSubmit()" name="userForm" novalidate class="form-horizontal" role="form">

            <div class="form-group">
                <label>Login:</label>
                <input type="text" class="form-control" ng-model="user.login">
            </div>

            <div class="form-group">
                <label>Password:</label>
                <input type="text" class="form-control" ng-model="user.password">
            </div>

            <div class="form-group">
                <label>Role:</label>
                <input type="text" class="form-control" ng-model="user.roles[0].roleName">
            </div>

            <div class="form-group">
                <label>First Name:</label>
                <input type="text" class="form-control" ng-model="user.firstName">
            </div>

            <div class="form-group">
                <label>Last Name:</label>
                <input type="text" class="form-control" ng-model="user.lastName">
            </div>

            <div class="form-group">
                <label>Middle Name:</label>
                <input type="text" class="form-control" ng-model="user.middleName">
            </div>

            <div class="form-group">
                <label>Birth Date:</label>
                <input type="text" class="form-control" ng-model="user.birthDate">
            </div>

            <div class="form-group">
                <label>Email:</label>
                <input type="text" class="form-control" ng-model="user.email">
            </div>

            <div class="form-group">
                <label>ADDRESS:</label>
            </div>

            <div class="form-group">
                <label>City:</label>
                <input type="text" class="form-control" ng-model="user.address.city">
            </div>

            <div class="form-group">
                <label>Street:</label>
                <input type="text" class="form-control" ng-model="user.address.street">
            </div>

            <div class="form-group">
                <label>House number:</label>
                <input type="text" class="form-control" ng-model="user.address.house">
            </div>
            <div class="form-group">
                <label>Apartment number:</label>
                <input type="text" class="form-control" ng-model="user.address.apartment">
            </div>

            <input type="submit" class="btn btn-default" value="Save"/>
        </form>

    </div>
</div>