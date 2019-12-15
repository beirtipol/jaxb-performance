# jaxb-performance
This project demonstrates a performance issue when trying to unmarshal the FIXRepository XML while you also have quickfixj-all on the classpath. You can safely remove the quickfixj-all dependency and the entire FIXRepository XML is unmarshalled in approx 3 seconds. When you include quickfixj-all in the dependencies, it takes up to 5 minutes to unmarshal the same XML
