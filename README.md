# yada

(<b>y</b>et <b>a</b>nother <b>d</b>ependency! <b>a</b>rrrrrgh!!!)

yada is a experimental REST library for Clojure, building on the ideas
within [Liberator](http://clojure-liberator.github.io/liberator/), and
from experiences using it on commercial projects. Its feature-set and
rationale is very similar to [Ratpack](http://www.ratpack.io/), but
designed for Clojure.

If you want to help, please join the discussion group [yada-discuss](https://groups.google.com/forum/#!forum/yada-discuss)

The full documentation is available at [http://yada.juxt.pro](http://yada.juxt.pro) or offline (see below).

## Installation

Add the following dependency to your `project.clj` file :-

```clojure
[yada "0.4.1"]
```

## Status

Please note that being a 0.x.y version indicates the provisional status
of this library, and that features are subject to change.

## Running documentation and examples offline

Although yada is a library, if you clone this repo you can run some
examples from the repl. This is a good way of seeing how far yada is
progressing.

```
cd yada
lein repl
```

Once the REPL starts, type in and run the following :-

```
user> (dev)
dev> (go)
```

Now browse to http://localhost:8090.

## Design goals

### Asynchronous support

REST APIs built on Ring and Liberator are built on a synchronous
abstraction which requires each API request to be serviced entirely by a
single thread.

In some situations this places limits to scalability. For example, when
information about a resource needs to be loaded from a remote datastore.

### Error handling

Ensure that when errors happen, they are handled properly.

### Data driven

Both Pedestal and bidi have shown the benefits that come from building
on data rather than functions or macros. For example, compare the amount
of code required to adapt bidi to Swagger compared to the same for
Compojure.

### First-class Swagger support

Documentation is a critical component of any web API, support for it
should be built in. Today, it seems like Swagger has built up a leading
role in defining what API documentation should feel like. It feels
pragmatic to treat Swagger as first-class, not as an after-thought.

### Security/CORS/CSRF support

These days, security needs to come by default, not as an exercise left
for the reader.

### Schema validation and coercions

Prismatic Schema has encourages the more widespread use of data
validation and coercion. This is particularly important for web APIs
which have to contend with externally presented data, injection attacks
and other malicious behaviour.

Also, there is now an explosion of data formats which might be necessary
to support, beyond JSON: bson, binary json, hessian, edn, yaml, json/ld,
json/xml. It is important that the data is agnostic to the format, and
coercions can eliminate the potential for programmer error by providing
default 'renderings' of a given data structure.

### Support for handling heavy load

> "When we need to support synchronous protocols like REST then use back pressure, signalled by our full incoming queue at the gateway, to send a meaningful “server busy” message such as the HTTP 503 status code.  The customer can then interpret this as time for a coffee and cake at the café down the road."
-- Martin Thompson, [Applying Back Pressure When Overloaded](http://mechanical-sympathy.blogspot.co.uk/2012/05/apply-back-pressure-when-overloaded.html)

HTTP has a 503 status code. This project aims to make it easier to
determine when this is the right thing to do, rather than leaving this
entirely to the developer.

## Design choices

Yada uses deferreds from @ztellman's
[Manifold library](https://github.com/ztellman/manifold). This provides
async options in the places in the HTTP implementation where it is most
needed: the determination of resource existence and the loading of the
metadata and data associated with a resource.

Unlike promises, deferreds also provide a way of signalling errors,
which is just what we need to handle and communicate errors properly.

## What's wrong with Liberator?

Not much. Liberator tries to do the 'right' thing in most cases. But
it's very general, and this comes at the cost of reduced
specialization. HTTP handling calls for specialization in places, so
even the best single abstraction will be ill-fitting at times.

### There are limits to the state machine abstraction

The original webmachine design using a state transition diagram has
served us well. However, there are a number of problems. I feel the
flowchart abstraction,  is _too_ abstract - it makes complex things
possible but inelegant at times, relying on tricks such as knowing which
decision functions to hijack, knowing what parts of the response
(e.g. response content type) have been determined when. For advanced
uses it requires initimate knowledge of the underlying flowchart. This
flowchart gives Liberator its flexibility, but the trade-off is that it
is tricky to do certain things, like adding CORS support, that should be
easier in a REST library.

### Too much abstraction, too much flexibility

Much the same can be said for solutions based on Ring middleware. The
abstraction underlying Ring middleware is the higher-order
function. Like Liberator, ordering issues come in to play, which break
the composeability.

- debugging: stack traces are unwieldly
- async is very difficult

### Data > API > DSL

A Liberator resource is almost like a map, but the values are mostly
functions. Where Liberator does accept data, such as which HTTP methods
are supported, this works well. Data is readily composed and
manipulated. Yada pushes the data model further.

## Comparison with Pedestal

### Similarities

Pedestal is built to support async.

### Differences

Pedestal combines routing with request handling. Yada uses
[Bidi](https://github.com/juxt/bidi) for the former and focusses on the
latter.

Pedestal uses an interceptor approach

Oh, and Yada has first-class support for Swagger.

## Comparison to other swagger libraries

(ref. devcon swagger talk)

Most other libraries force developers to choose one of these development processes:

* Define the swappger spec first - generate implementation stubs
* Use annotations in the implementation code and generate the swagger specs

Code generation techniques remove the option to change the API once the specs have been developed. Annotations on the implementation code are not ideal either. Yada uses a machine-readable information model. This information model is augmented with developer-provided values and callbacks to implement the API. The API documentation and implementation are therefore much more closely aligned.

### Other references

[octohipster](https://github.com/myfreeweb/octohipster)
[ring-swagger](https://github.com/metosin/ring-swagger)
[Ratpack](https://ratpack.io)
[Swagger](https://swagger.io)

## Copyright & License

The MIT License (MIT)

Copyright © 2015 JUXT LTD.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
