Example of rest body:
```json

{
  "firstDistance": {
    "amount": "1",
    "lengthUnit": "ft"
  },
  "secondDistance": {
    "amount": "2",
    "lengthUnit": "M"
  },
  "resultUnit": "m"
}
```
Exposed APIs (POST):

/api/add </br>
/api/subtract </br>
/api/multiply </br>
/api/divide </br>

Lenght Units (and result unit) are case insensitive, usage of FT is the same as ft. </br>
Allowed units are: FT, M, NM

