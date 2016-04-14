function wrapUserDate(user) {
    user.birthDate = new Date(user.birthDate);
}

function wrapConsignmentDate(consignment) {
    //wrap 3 dates
}

function getUserRoles() {
    return [{roleName: 'ROLE_SYSTEM_ADMIN'}, {roleName: 'ROLE_ADMIN'}, {roleName: 'ROLE_DISPATCHER'}, {roleName: 'ROLE_MANAGER'}, {roleName: 'ROLE_CONTROLLER'}, {roleName: 'ROLE_OWNER'}];
}

function getStatuses() {
    return ['ACTIVE', 'BLOCKED'];
}