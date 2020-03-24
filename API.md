https://github.com/sonicfrog-z/final_project/wiki

Print all products
----

  Returns String value of all products info.

* **URL**

  /printAllProducts

* **Method:**

  `GET`
  
*  **URL Params**

   None

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `SELECT * from product: 1256, Gear - Large 4437`

* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** None

* **Sample Call:**

  ```shell
  curl http://localhost:8080/printAllProducts
  ```



Print all regions
----

  Returns String value of all regions info.

* **URL**

  /printAllRegions

* **Method:**

  `GET`

* **URL Params**

  None

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `SELECT * from region: 101, North America`

* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** None

* **Sample Call:**

  ```shell
  curl http://localhost:8080/printAllRegions
  ```





Print all sales
----

  Returns String value of all sales info.

* **URL**

  /printAllSales

* **Method:**

  `GET`

* **URL Params**

  None

* **Data Params**

  None

* **Success Response:**

  - **Code:** 200 <br />
    **Content:** 

    All sales: 

    Year   Month  Region                  Product Name  Sales 

    2020,          1 , USA-Northeast ,  Gear - Large ,    1000.0

* **Error Response:**

  - **Code:** 404 NOT FOUND <br />
    **Content:** None

* **Sample Call:**

  ```shell
  curl http://localhost:8080/printAllSales
  ```



Add sales
----

  Post json data of sales and add to database, returns number.

* **URL**

  /addSales

* **Method:**

  `POST`

* **Data Params**

  `product_id=[integer]`

  `region_id=[integer]`

  `year=[integer]`

  `month=[integer]`

  `sales=[double]`

  

* **Success Response:**

  - **Code:** 200 <br />
    **Content:** 

    > Rows updated: 1

* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** None

* **Sample Call:**

  ```shell
  curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data "{\"product_id\": \"5567\", \"region_id\": \"101\", \"year\": \"2021\", \"month\": \"1\", \"sales\":\"2500\"}" "http://localhost:8080/addUser"
  ```

