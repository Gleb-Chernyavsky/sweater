<#import "parts/loging.ftlh" as l>
<#import "parts/common.ftlh" as c>

<@c.page>
    ${message!}
    <@l.login "/login" false />
</@c.page>