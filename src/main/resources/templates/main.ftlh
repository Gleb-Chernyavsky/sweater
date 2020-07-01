<#import "parts/common.ftlh" as c>
<#import "parts/loging.ftlh" as l>

<@c.page>
    <@l.logout/>
    <div>
        <form method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="text" name="text" placeholder="Введите сообщение" />
            <input type="text" name="tag" placeholder="Tag" >
            <button type="submit">Добавить</button>
        </form>
    </div>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter!}">
        <button type="submit">Найти</button>
    </form>
    <div>Список сообщений</div>
    <#list messages as message>
            <div>
                <b>${message.id}</b>
                <span>${message.text}</span>
                <i>${message.tag}</i>
                <strong>${message.authorName}</strong>
            </div>
        <#else>
        No message
    </#list>
</@c.page>
