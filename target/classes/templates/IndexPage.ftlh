<#-- @ftlvariable name="books" type="ru.itmo.wp.domain.Book[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">Book</div>
        <table>
            <thead>
            <tr>
                <th>Cipher</th>
                <th>Name</th>
                <th>Author</th>
                <th>Create</th>
            </tr>
            </thead>
            <tbody>
            <#if books?size==0>
                <tr class="noData">
                    <td colspan="4">
                        No data
                    </td>
                </tr>
            <#else>
                <#list books as book>
                    <tr>
                        <td class="Cipher">${book.cipher}</td>
                        <td class="Name">${book.name}</td>
                        <td class="Author">${book.author}</td>
                        <td class="Create">${book.creationTime}</td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>
