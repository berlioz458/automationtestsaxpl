<html>
<#-- @ftlvariable name="data" type="io.qameta.allure.attachment.http.HttpResponseAttachment" -->
<head>
<style>
.code {
background-color: rgba(0, 120, 201, 0.2);
border: dashed blue;
font-family: monospace;
}
</style>

<style>
pre {
white-space: pre-wrap;
}
</style>
</head>
<body>
<h4>Status code</h4>
<div class="code">
    <#if data.responseCode??>
        <pre><code><b>${data.responseCode}</b></code></pre>
    <#else>Unknown</#if>
</div>
<#if data.url??>
    <div class="code">
    <pre><code>${data.url}</code></pre>
    </div></#if>

<#if (data.headers)?has_content>
    <h4>Headers</h4>
    <div class="code">
        <#list data.headers as name, value>
            <div>
                <pre><code><b>${name}</b>: ${value}</code></pre>
            </div>
        </#list>
    </div>
</#if>

<#if data.body??>
    <h4>Body</h4>
    <div class="code">
        <pre><code>${data.body}</code></pre>
    </div>
</#if>

<#if (data.cookies)?has_content>
    <h4>Cookies</h4>
    <div>
        <#list data.cookies as name, value>
            <div class="code">
                <pre><code><b>${name}</b>: ${value}</code></pre>
            </div>
        </#list>
    </div>
</#if>
</body>
</html>