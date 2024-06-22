# Read Me First

```
C:\tools\kafka_2.13-3.7.0\bin\windows

zookeeper-server-start ../../config/zookeeper.properties
kafka-server-start ../../config/server.properties
connect-standalone ../../config/connect-standalone.properties

```

### Web flux examples

##### 2023-05-08

- Webflux filters checked with global functionality. 
- WebExceptionHandler implemented
- Json annotation worked properly
- Custom Serializer/Deserializer is tested
- Errors message handling updated.

### Standard API Documents 1.2



https://jsonapi.org/format/1.2/#document-top-level

A document MUST contain at least one of the following top-level members:

- data

the document’s “primary data”.

- errors

an array of error objects.

- meta

a meta object that contains non-standard meta-information.

a member defined by an applied extension.


#### errors 

errors should be arrays []

Error objects MUST be returned as an array keyed by errors in the top level of a JSON:API document.

An error object MAY have the following members, and MUST contain at least one of:


- id
    id: a unique identifier for this particular occurrence of the problem.
- links
    links: a links object that MAY contain the following members:
- about

about: a link that leads to further details about this particular occurrence of the problem. When derefenced, this URI SHOULD return a human-readable description of the error.

- type

type: a link that identifies the type of error that this particular error is an instance of. This URI SHOULD be dereferencable to a human-readable explanation of the general error.

- status 
   
status: the HTTP status code applicable to this problem, expressed as a string value. This SHOULD be provided.

- code

code: an application-specific error code, expressed as a string value.

- title

title: a short, human-readable summary of the problem that SHOULD NOT change from occurrence to occurrence of the problem, except for purposes of localization.

- detail

detail: a human-readable explanation specific to this occurrence of the problem. Like title, this field’s value can be localized.

- source

source: an object containing references to the primary source of the error. It SHOULD include one of the following members or be omitted:
        pointer: a JSON Pointer [RFC6901] to the value in the request document that caused the error [e.g. "/data" for a primary data object, or "/data/attributes/title" for a specific attribute]. This MUST point to a value in the request document that exists; if it doesn’t, the client SHOULD simply ignore the pointer.
        parameter: a string indicating which URI query parameter caused the error.
        header: a string indicating the name of a single request header which caused the error.

- meta

meta: a meta object containing non-standard meta-information about the error.


```
{
  "errors": [
     {
        "id": "T5830",
        "links": "localhost://api/standards",
        "about": "The request id should not be empty",
        "type": "Numbers Format",
        "status": "Not Found",
        "code": "55780",
        "title": "Account Details Not Found",
        "detail": "User not linked with any Account details not found",
        source: {
            "pointer": "or"
            "header": "one of them",
            "parameter": ""
            
        }
    }
  ] 
}

```

#### Meta Information

Where specified, a meta member can be used to include non-standard meta-information. The value of each meta member MUST be an object (a “meta object”).

Any members MAY be specified within meta objects.

```
{
  "meta": {
    "copyright": "Copyright 2015 Example Corp.",
    "authors": [
      "Yehuda Katz",
      "Steve Klabnik",
      "Dan Gebhardt",
      "Tyler Kellen"
    ]
  },
  "data": {
    // ...
  }
}

```

Nginx Reverse Proxy Configuration

```

server {
        listen       80;
        server_name  localhost;

        location /test {
			rewrite /test/(.*) /$1  break;
			proxy_set_header X-Request-ID test;		
            proxy_pass http://localhost:8080;
        }
		
		location /labs {
			rewrite /labs/(.*) /$1  break;
			proxy_set_header X-Request-ID labs;
            proxy_pass http://localhost:8080;
        }
		
		location /srimalar {
			rewrite /srimalar/(.*) /$1  break;
			proxy_set_header X-Request-ID srimalar;
            proxy_pass http://localhost:8080;
        }
    }
    
```