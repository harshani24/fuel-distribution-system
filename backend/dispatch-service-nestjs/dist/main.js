"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const core_1 = require("@nestjs/core");
const microservices_1 = require("@nestjs/microservices");
const app_module_1 = require("./app.module");
const cors = require('cors');
async function bootstrap() {
    const kafkaHost = process.env.KAFKA_HOST || 'localhost';
    const kafkaPort = process.env.KAFKA_PORT || '9092';
    const app = await core_1.NestFactory.create(app_module_1.AppModule);
    app.connectMicroservice({
        transport: microservices_1.Transport.KAFKA,
        options: {
            client: {
                brokers: [`${kafkaHost}:${kafkaPort}`],
            },
            consumer: {
                groupId: 'dispatch-group',
            },
        },
    });
    app.enableCors();
    app.use(cors({ origin: 'http://localhost:3000' }));
    await app.startAllMicroservices();
    await app.listen(8194, () => 'start the dispatch service.....');
}
bootstrap();
//# sourceMappingURL=main.js.map