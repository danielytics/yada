It's possible to set the status and headers in the resource-map, just
like you would in a Ring response map.

<resource-map/>

<request/>

<response/>

However, it's better to omit the status and only add headers when you
absolutely need to. Yada will use the information available to it to set
the status and headers on the response automatically. This is the
primary reason for using a library like yada.
