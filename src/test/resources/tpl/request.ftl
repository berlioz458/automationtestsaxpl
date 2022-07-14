<html>
<#-- @ftlvariable name="data" type="io.qameta.allure.attachment.http.HttpRequestAttachment" -->
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

<div class="code">
    <#if data.name??>
        <h4>${data.name}</h4>
    </#if>
    <pre><code><#if data.method??>${data.method}<#else>GET</#if>: <#if data.url??>${data.url}<#else>Unknown</#if></code></pre>
</div>

<#if data.body??>
    <h4>Body</h4>
    <div class="code">
        <pre><code>${data.body}</code></pre>
    </div>
</#if>


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


<#if (data.cookies)?has_content>
    <h4>Cookies</h4>
    <div class="code">
        <#list data.cookies as name, value>
            <div>
                <pre><code><b>${name}</b>: ${value}</code></pre>
            </div>
        </#list>
    </div>
</#if>

<#if data.curl??>
    <h4>Curl</h4>
    <div class="code">
        <pre><code>${data.curl}</code></pre>
    </div>
</#if>
</body>
</html>