Language Service
=======

To consume the post method, just consult the following url:
`207.154.197.207:1313/code`

Where _code_ is a JSON, the structure is about:

```json
{
  "lang": "Some language (Python, C or Java)",
  "code": "Block of code"
}
``` 

In this moment, the service support Python, C and Java, but to implement another language is easy.