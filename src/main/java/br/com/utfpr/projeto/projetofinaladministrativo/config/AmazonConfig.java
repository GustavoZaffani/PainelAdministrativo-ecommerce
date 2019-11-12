package br.com.utfpr.projeto.projetofinaladministrativo.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    private static final String ACCESS_KEY = "AKIAJPCT7YSR5IUUDM4A";
    private static final String SECRET_KEY = "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";
    private static final String REGION = "sa-east-1";

    @Bean
    public BasicAWSCredentials basicAWSCredentials() {
        return new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
    }

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard().withRegion(REGION)
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials())).build();
    }
}
