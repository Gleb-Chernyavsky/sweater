<#import "parts/loging.ftlh" as l>
<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="mb-1">Add new User</div>
${message!""}
    <@l.login "/registration" true/>
</@c.page>