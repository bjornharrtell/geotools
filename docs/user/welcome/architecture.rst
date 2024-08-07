Architecture
============

Having an understanding of how the GeoTools library is structured is helpful for sorting
out what jars you need to include with your application.

This page introduces you to the modules of the GeoTools library and how they fit together.
By including only the jars you need you can select just the right amount of GeoTools for
your next project.

GeoTools Library
----------------

The GeoTools library forms a software "stack" with each module building on the ideas
and concepts defined in the previous one.

.. figure:: /images/geotools.svg
   
   GeoTools Module Architecture

Each layer of the "stack" is built on the ones below it:

* To use Referencing you require ``gt-api``, ``gt-metadata`` and ``gt-referencing``. To make use of
  "EPSG" codes you need one plugin, such as ``gt-epsg-hsql``, to supply the EPSG definitions to the 
  Referencing module.
  
* To use Coverage you require ``gt-api``, ``gt-metadata``, ``gt-referencing`` (with plugin ``gt-epsg-hsql``),
  ``gt-main``, and ``gt-covearge``. To make full use of the Coverage module a plugin such as ``gt-geotiff`` is
  used to access GeoTIFF files.

The maven tool can calculate the jars you need, please see the Eclipse or Netbeans quickstart
for an example use.

================== ==============================================================
Module                Purpose
================== ==============================================================
``gt-render``      Map API, with Java2D rendering engine for mapping.
``gt-jdbc``        Implements for accessing spatial database
``gt-xml``         Implements of common spatial XML formats
``gt-cql``         Implements of Common Query Language for filters
``gt-coverage``    Implementation for accessing raster information
``gt-main``        Data API, with default implementations of filter, feature, etc...
``gt-referencing`` Implementation of co-ordinate location and transformation
``gt-metadata``    Implementation of identification and description
``gt-api``         Definition of interfaces for common spatial concepts
``jts``            JTS Topology Suite (external library) implementing Geometry
================== ==============================================================


GeoTools Plugins
^^^^^^^^^^^^^^^^

GeoTools offers plug-ins to support additional data formats, different coordinate reference
system authorities and so on.

+---------------------+------------------------+--------------------------------------+
| Module              | JAR                    | Plugin                               |
+=====================+========================+======================================+
| ``gt-render``       |                        |                                      |
+---------------------+------------------------+--------------------------------------+
| ``gt-jdbc``         | ``gt-jdbc-db2``        | Geometry in DB2                      |
+---------------------+------------------------+--------------------------------------+
|                     | ``gt-jdbc-h2``         | Pure Java "H2" database              |
+---------------------+------------------------+--------------------------------------+
|                     | ``gt-jdbc-mysql``      | Geometry in MySQL                    |
+---------------------+------------------------+--------------------------------------+
|                     | ``gt-jdbc-oracle``     | Oracle SDO Geometry                  |
+---------------------+------------------------+--------------------------------------+
|                     | ``gt-jdbc-postgis``    | PostgreSQL extension PostGIS         |
+---------------------+------------------------+--------------------------------------+
|                     | ``gt-jdbc-sqlserver``  | SQL Server                           |
+---------------------+------------------------+--------------------------------------+
|                     | ``gt-jdbc-hana``       | SAP HANA                             |
+---------------------+------------------------+--------------------------------------+
| ``gt-xml``          |                        |                                      |
+---------------------+------------------------+--------------------------------------+
| ``gt-cql``          |                        |                                      |
+---------------------+------------------------+--------------------------------------+
| ``gt-main``         | ``gt-shape``           | Shapefile read/write support         |
+---------------------+------------------------+--------------------------------------+
|                     | ``gt-wfs``             | WFS read/write support               |
+---------------------+------------------------+--------------------------------------+
| ``gt-coverage``     | ``gt-geotiff``         | GeoTIFF raster format                |
+---------------------+------------------------+--------------------------------------+
|                     | ``gt-arcgrid``         | arcgrid format                       |
+---------------------+------------------------+--------------------------------------+
|                     | ``gt-mif``             | MIF format                           |
+---------------------+------------------------+--------------------------------------+
|                     | ``gt-image``           | JPG, PNG, TIFF formats               |
+---------------------+------------------------+--------------------------------------+
| ``gt-referencing``  | ``epsg-access``   .    | Official EPSG database in Access     |
+---------------------+------------------------+--------------------------------------+
|                     | ``epsg-hsql``          | Pure Java port of EPSG database      |
+---------------------+------------------------+--------------------------------------+
|                     | ``epsg-wkt``           | Lightweight copy of EPSG codes       |
+---------------------+------------------------+--------------------------------------+
|                     | ``epsg-postgresql``    | PostgreSQL port of EPSG database     |
+---------------------+------------------------+--------------------------------------+
| ``gt-metadata``     |                        |                                      |
+---------------------+------------------------+--------------------------------------+
| ``gt-api``          |                        |                                      |
+---------------------+------------------------+--------------------------------------+
| ``jts``             |                        |                                      |
+---------------------+------------------------+--------------------------------------+

Usually at least one plug-in is needed for each layer for GeoTools to do something. As an
example every time you use the referencing module please make sure you have an EPSG plugin
around (or the referencing module will not know that "EPSG:4326" is the world as we know it).

GeoTools Extensions
-------------------

We have gone a bit further and implemented some interesting "extensions" on top of the GeoTools library. These extensions provide additional capabilities that are built on top of GeoTools using the full functionality of the core library.

.. figure:: /images/extensions.svg
   
   GeoTools Extensions

The extensions are independent of each other offering and may be of use in your application. Here is a brief listing of the extensions at the time of writing.

================== ====================================================
JAR                Extension
================== ====================================================
``gt-app-schema``  Map from application schema to complex feature model
``gt-brewer``      Generation of styles using color brewer
``gt-complex``     Support for making custom complex feature model
``gt-graph``       Work with graph and network traversals
``gt-grid``        Dynamicly generate features from grid definiton
``gt-transform``   Transform features on the fly
``gt-wms``         Web Map Server client
``gt-wmts``        Web Map Tile Server client
``gt-xsd``         Parsing/Encoding for common OGC schemas
================== ====================================================

XML
^^^

To support the XML module in GeoTools we have bundled up several XML schemas in JAR form (to prevent needing to download them from
the Internet each time they are needed). In addition these jars contain a generated Java data structure produced with the Eclipse Modeling Framework.

=================== =============================================
JAR                 Schema
=================== =============================================
``net.opengis.ows``   open web services schema
``net.opengis.wfs``   web feature service
``net.opengis.wps``   web processing service schema
``net.opengis.wcs``   web coverage service schema
``net.opengis.wfs``   web feature service schema
``org.w3.xlink``      XLink schema
=================== =============================================

These facilities are used by the XSD parser by way of a series of XSD plugins. These plugins indicating how to parse and encode
additional content using Eclipse XSD library to
parse XML schema documents and offer "bindings" showing how to parse and encode to Java classes such as String,
Date, URL and Geometry.

=================== =============================================
JAR                 Bindings
=================== =============================================
``gt-xsd-core``       Basic types defined by XML schema
``gt-xsd-fes``        filter 2.0
``gt-xsd-filter``     filter (used by OGC CAT and WFS)
``gt-xsd-kml``        keyhole markup language
``gt-xsd-wfs``        web feature service
``gt-xsd-wps``        web processing service
``gt-xsd-gml3``       geographic markup language 3
``gt-xsd-gml2``       geographic markup language 2
``gt-xsd-ows``        open web services
``gt-xsd-wcs``        web coverage service
``gt-xsd-wms``        web map service
``gt-xsd-sld``        style layer descriptor
=================== =============================================

GeoTools Unsupported
--------------------

There are also a number of "unsupported" plugins and extensions. These modules are not
distributed by the project management committee as part of the GeoTools download,
however they are available via maven or individual download.

+-------------------+-----------------------------------------+
| Unsupported       | Purpose                                 |
+===================+=========================================+
| ``gt-swt``        | Standard widget toolkit interactive map |
+-------------------+-----------------------------------------+
| ``gt-swing``      | Swing interactive map                   |
+-------------------+-----------------------------------------+
| ``gt-oracle``     | retired oracle support                  |
+-------------------+-----------------------------------------+
| ``gt-postgis``    | retired PostGIS support                 |
+-------------------+-----------------------------------------+
| ``gt-db2``        | retired db2 support                     |
+-------------------+-----------------------------------------+
| ``gt-wps``        | Web Processing Service client           |
+-------------------+-----------------------------------------+
| ``gt-process``    | Job system for spatial data             |
+-------------------+-----------------------------------------+
