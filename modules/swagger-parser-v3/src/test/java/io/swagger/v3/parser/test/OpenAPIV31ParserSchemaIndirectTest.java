package io.swagger.v3.parser.test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.swagger.v3.core.util.Yaml31;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class OpenAPIV31ParserSchemaIndirectTest {
    protected int serverPort = getDynamicPort();
    protected WireMockServer wireMockServer;

    private static int getDynamicPort() {
        return new Random().ints(10000, 20000).findFirst().getAsInt();
    }

    @BeforeClass
    private void setUpWireMockServer() throws IOException {
        this.wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        this.wireMockServer.start();
        this.serverPort = wireMockServer.port();
        WireMock.configureFor(this.serverPort);


        String pathFile = FileUtils.readFileToString(new File("src/test/resources/3.1.0/dereference/schemaindirect/root.json"));
        pathFile = pathFile.replace("${dynamicPort}", String.valueOf(this.serverPort));

        WireMock.stubFor(get(urlPathMatching("/schemaindirect/root.json"))
                .willReturn(aResponse()
                        .withStatus(HttpURLConnection.HTTP_OK)
                        .withHeader("Content-type", "application/json")
                        .withBody(pathFile
                                .getBytes(StandardCharsets.UTF_8))));

        pathFile = FileUtils.readFileToString(new File("src/test/resources/3.1.0/dereference/schemaindirect/nested/domain.yaml"));
        pathFile = pathFile.replace("${dynamicPort}", String.valueOf(this.serverPort));

        WireMock.stubFor(get(urlPathMatching("/schemaindirect/nested/domain.yaml"))
                .willReturn(aResponse()
                        .withStatus(HttpURLConnection.HTTP_OK)
                        .withHeader("Content-type", "application/yaml")
                        .withBody(pathFile
                                .getBytes(StandardCharsets.UTF_8))));


        pathFile = FileUtils.readFileToString(new File("src/test/resources/3.1.0/dereference/schemaindirect/ex.json"));
        pathFile = pathFile.replace("${dynamicPort}", String.valueOf(this.serverPort));

        WireMock.stubFor(get(urlPathMatching("/schemaindirect/ex.json"))
                .willReturn(aResponse()
                        .withStatus(HttpURLConnection.HTTP_OK)
                        .withHeader("Content-type", "application/json")
                        .withBody(pathFile
                                .getBytes(StandardCharsets.UTF_8))));

        pathFile = FileUtils.readFileToString(new File("src/test/resources/3.1.0/dereference/schemaindirect/ex1.json"));
        pathFile = pathFile.replace("${dynamicPort}", String.valueOf(this.serverPort));

        WireMock.stubFor(get(urlPathMatching("/schemaindirect/ex1.json"))
                .willReturn(aResponse()
                        .withStatus(HttpURLConnection.HTTP_OK)
                        .withHeader("Content-type", "application/json")
                        .withBody(pathFile
                                .getBytes(StandardCharsets.UTF_8))));

        pathFile = FileUtils.readFileToString(new File("src/test/resources/3.1.0/dereference/schemaindirect/ex2.json"));
        pathFile = pathFile.replace("${dynamicPort}", String.valueOf(this.serverPort));

        WireMock.stubFor(get(urlPathMatching("/schemaindirect/ex2.json"))
                .willReturn(aResponse()
                        .withStatus(HttpURLConnection.HTTP_OK)
                        .withHeader("Content-type", "application/json")
                        .withBody(pathFile
                                .getBytes(StandardCharsets.UTF_8))));

        pathFile = FileUtils.readFileToString(new File("src/test/resources/3.1.0/dereference/schemaindirect/ex1a.json"));
        pathFile = pathFile.replace("${dynamicPort}", String.valueOf(this.serverPort));

        WireMock.stubFor(get(urlPathMatching("/schemaindirect/ex1a.json"))
                .willReturn(aResponse()
                        .withStatus(HttpURLConnection.HTTP_OK)
                        .withHeader("Content-type", "application/json")
                        .withBody(pathFile
                                .getBytes(StandardCharsets.UTF_8))));

        pathFile = FileUtils.readFileToString(new File("src/test/resources/3.1.0/dereference/schemaindirect/nested/ex2a.json"));
        pathFile = pathFile.replace("${dynamicPort}", String.valueOf(this.serverPort));

        WireMock.stubFor(get(urlPathMatching("/schemaindirect/nested/ex2a.json"))
                .willReturn(aResponse()
                        .withStatus(HttpURLConnection.HTTP_OK)
                        .withHeader("Content-type", "application/json")
                        .withBody(pathFile
                                .getBytes(StandardCharsets.UTF_8))));

        pathFile = FileUtils.readFileToString(new File("src/test/resources/3.1.0/dereference/schemaindirect/nested/ex3a.json"));
        pathFile = pathFile.replace("${dynamicPort}", String.valueOf(this.serverPort));

        WireMock.stubFor(get(urlPathMatching("/schemaindirect/nested/ex3a.json"))
                .willReturn(aResponse()
                        .withStatus(HttpURLConnection.HTTP_OK)
                        .withHeader("Content-type", "application/json")
                        .withBody(pathFile
                                .getBytes(StandardCharsets.UTF_8))));

        pathFile = FileUtils.readFileToString(new File("src/test/resources/3.1.0/dereference/schemaindirect/ex1schema.json"));
        pathFile = pathFile.replace("${dynamicPort}", String.valueOf(this.serverPort));

        WireMock.stubFor(get(urlPathMatching("/schemaindirect/ex1schema.json"))
                .willReturn(aResponse()
                        .withStatus(HttpURLConnection.HTTP_OK)
                        .withHeader("Content-type", "application/json")
                        .withBody(pathFile
                                .getBytes(StandardCharsets.UTF_8))));

        pathFile = FileUtils.readFileToString(new File("src/test/resources/3.1.0/dereference/schemaindirect/ex2schema.json"));
        pathFile = pathFile.replace("${dynamicPort}", String.valueOf(this.serverPort));

        WireMock.stubFor(get(urlPathMatching("/schemaindirect/ex2schema.json"))
                .willReturn(aResponse()
                        .withStatus(HttpURLConnection.HTTP_OK)
                        .withHeader("Content-type", "application/json")
                        .withBody(pathFile
                                .getBytes(StandardCharsets.UTF_8))));

        pathFile = FileUtils.readFileToString(new File("src/test/resources/3.1.0/dereference/schemaindirect/ex3schema.json"));
        pathFile = pathFile.replace("${dynamicPort}", String.valueOf(this.serverPort));

        WireMock.stubFor(get(urlPathMatching("/schemaindirect/ex3schema.json"))
                .willReturn(aResponse()
                        .withStatus(HttpURLConnection.HTTP_OK)
                        .withHeader("Content-type", "application/json")
                        .withBody(pathFile
                                .getBytes(StandardCharsets.UTF_8))));

    }

    @AfterClass
    private void tearDownWireMockServer() {
        this.wireMockServer.stop();
    }


    @Test
    public void testFull() throws Exception {
        ParseOptions p = new ParseOptions();
        p.setResolve(true);
        String uri = "http://localhost:" + this.serverPort + "/schemaindirect/root.json";
        SwaggerParseResult swaggerParseResult = new OpenAPIV3Parser().readLocation(uri, null, p);
        org.testng.Assert.assertEquals(Yaml31.pretty(swaggerParseResult.getOpenAPI()), FileUtils.readFileToString(new File("src/test/resources/3.1.0/dereference/schemaindirect/dereferenced.yaml")));
    }
}