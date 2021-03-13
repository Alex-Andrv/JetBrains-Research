<#-- @ftlvariable name="addBookForm" type="ru.itmo.wp.domain.Book" -->

<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="form">
        <div class="header">Add Book</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="addBook"/>
                <div class="field">
                    <div class="name">
                        <label for="name">Name</label>
                    </div>
                    <div class="value">
                        <input autofocus id="name" name="name" value="${addBookForm.name!}"/>
                    </div>
                    <@c.error "addBookForm.name"/>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="author">Author</label>
                    </div>
                    <div class="value">
                        <input autofocus id="author" name="author" value="${addBookForm.author!}"/>
                    </div>
                    <@c.error "addBookForm.author"/>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="cipher">Cipher</label>
                    </div>
                    <div class="value">
                        <input autofocus id="cipher" name="cipher" value="${addBookForm.cipher!}"/>
                    </div>
                    <@c.error "addBookForm.cipher"/>
                </div>
                <div class="button-field">
                    <input type="submit" value="Add">
                </div>
            </form>
        </div>
    </div>
</@c.page>
