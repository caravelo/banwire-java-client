# Banwire Java Client

Java Client for Banwire's payments platforms API

## Supported operations

 - ✅ Tokenise card (⚠️ this call must not be used in production environments since it will increase your PCI scope)
 - ✅ Purchase
 - ❌ Refund

## Installation
TODO: complete once published in MVN Central

## Usage

### Initialisation

    BanwireClient banwire = new BanwireClient(HOST, USER);

### Tokenise a new card

     Map<String, Object> parameters = new HashMap<>();
     parameters.put("number", "5134422031476272");
     parameters.put("exp_month", 12);
     parameters.put("exp_year", 19);
     parameters.put("cvv", 162);
     parameters.put("name", "Test Test");
     parameters.put("address", "123, Valhalla Lane");
     parameters.put("postal_code", 12345);
     parameters.put("email", "test@test.com");
     parameters.put("phone", "+1 987654321");

     TokenisedCard cardToken = banwire.tokenise(parameters);

### Tokenise a card that already exists in Banwire

    banwire.tokenise(parameters, true);

### Purchase

    Map<String, Object> parameters = new HashMap<>();
    parameters.put("reference", "MY-ORDER-12345");
    parameters.put("token", "crd.1jJckdSrFY3uAVZecM1voax1gLhv");
    parameters.put("amount", 100);

    Purchase cardToken = banwire.purchase(parameters);
