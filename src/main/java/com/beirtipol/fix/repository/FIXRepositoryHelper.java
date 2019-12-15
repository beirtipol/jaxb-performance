package com.beirtipol.fix.repository;

import fixrepository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.Optional;

@Repository
public class FIXRepositoryHelper {
    private static final Logger LOG = LoggerFactory.getLogger(FIXRepositoryHelper.class);
    private static final String REPOSITORY_FILENAME = "FixRepository.xml";
    private static final String PHRASES_FILENAME = "FIX.5.0SP2_EP249_en_phrases.xml";
    private JAXBContext context;
    private FixRepository repository;
    private Phrases phrases;
    @Autowired
    private FIXRepositoryConfig config;

    public FIXRepositoryHelper() {

    }

    @PostConstruct
    private void initialize() {
        try {
            LOG.info("Creating JAXB Context");
            context = JAXBContext.newInstance(FixRepository.class);
            LOG.info("Loading Repository XML");
            long start = System.nanoTime();
            repository = loadXML(config.getRepositoryFilePath());
            LOG.info("Loaded Repository XML in " + (System.nanoTime() - start) / 1000000 + "ms");
            LOG.info("Loading Phrases XML");
            start = System.nanoTime();
            phrases = loadXML(config.getPhrasesFilePath());
            LOG.info("Loaded Phrases XML in " + (System.nanoTime() - start) / 1000000 + "ms");
            LOG.info("Loaded JAXB Objects");
        } catch (JAXBException e) {
            LOG.error("Could not create JAXBContext", e);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T loadXML(String filename) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        String path = FIXRepositoryHelper.class.getClassLoader().getResource(filename).getPath();
        LOG.info("Loading from " + path);
        return (T) unmarshaller.unmarshal(new File(path));
    }

    public FixRepository getRepository() {
        return repository;
    }

    public Optional<Field> loadFieldInfo(int tag) {
        List<Fix> fixes = repository.getFix();
        Fix fix = fixes.get(0);
        Fields fields = fix.getFields();
        Optional<Field> result = fields.getField().stream().filter(f -> f.getId().intValue() == tag).findFirst();
        return result;
    }
}
