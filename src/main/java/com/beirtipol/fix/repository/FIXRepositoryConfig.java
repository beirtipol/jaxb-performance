package com.beirtipol.fix.repository;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootConfiguration
@ConfigurationProperties(prefix = "fix.repository")
public class FIXRepositoryConfig {
    private String repositoryFilePath;
    private String phrasesFilePath;

    public String getRepositoryFilePath() {
        return repositoryFilePath;
    }

    public void setRepositoryFilePath(String repositoryFilePath) {
        this.repositoryFilePath = repositoryFilePath;
    }

    public String getPhrasesFilePath() {
        return phrasesFilePath;
    }

    public void setPhrasesFilePath(String phrasesFilePath) {
        this.phrasesFilePath = phrasesFilePath;
    }
}
