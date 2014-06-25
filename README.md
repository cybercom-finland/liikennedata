Liikennedata
============

Liikennedata project in Big Data spirit.

![Liikennedata high-level architecture](bus_data.png?raw=true "Liikennedata high-level architecture")

Guiding Principles
------------------

* Everything is open source, published in GitHub.
* The data storage instance is deployed in Cybercom Cloud.
* The data storage instance is a normal JSON/HTTP client to Siri Access.
* The data storage instance stores the data to Hadoop.
* Hadoop uses a managed volume in Cybercom Cloud to store the massive amount of data.
* The frontend is scalable through Nginx load balancing.
* The backend is scalable through Hadoop distribution and general caching.
* There is a public read/query access to Hadoop for researchers.
* There is a public web UI for easy use.
* There is a public interface for REST queries to Data Views Service.
* Data is provisioned through efficient views that provide cached, real-time, precomputed and aggregated parametrized views to the data.
 * Each view has relevant windowing limits; larger summarizations of data can only be accessed through views specialized for large history summarizations (pre-computed, pre-rasterized).
* Redis is used as a rich cache for current data to enable real-time views and summaries.

Link to the internal wiki: https://confluence.cybercom.com/display/FIBDTF/FI+-+Liikennedata+Home
