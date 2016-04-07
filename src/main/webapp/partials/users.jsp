<div class="page-header">
    <h1>Users</h1>
</div>
<div class="row">

    <div class="col-md-12">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Full Name</th>
                <th>Login</th>
                <th>Role</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="user in users">
                <td>
                    <a ui-sref="userDetails({userId: user.id})">{{ user.firstName + ' ' + user.lastName }}</a>
                </td>
                <td>
                    {{ user.login }}
                </td>
                <td>
                    {{ user.roles[0].roleName }}
                </td>
            </tbody>
        </table>
    </div>

</div>