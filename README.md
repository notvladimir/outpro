# Attribute & Options Application

## API documentation

Each endpoint is well documented with OpenAPI 3.x specification.
- Swagger UI Path: http://localhost:8080/api/profile/swagger-ui

## JSON Format

### Attributes:
```JSON
[
    {
        "code": "supplierpacksize",
        "labels": [
            {
                "locale": "en_IE",
                "value": "Number in Package from Supplier"
            }
        ]
    },
    {
        "code": "packsize",
        "labels": [
            {
                "locale": "en_IE",
                "value": "Number in Package"
            }
        ]
    }
]
```

### Attribute with options:
```JSON
{
  "code": "helmetsize",
  "labels": [
    {
      "locale": "en_IE",
      "value": "Helmet Sizes"
    }
  ],
  "options": [
    {
      "code": "35mm",
      "labels": [
        {
          "locale": "en_IE",
          "value": "35 mm"
        }
      ],
      "attributeCode": "helmetsize",
      "sortOrder": 0
    }
  ]
}
```

## Requirements

- Java 21
- Maven 3.8

## How to run :rocket:

To run this project run maven command:

```bash
$ mvn spring-boot:run
```

## Testing

```bash
$ mvn test
```




