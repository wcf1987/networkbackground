/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50742
 Source Host           : localhost:3306
 Source Schema         : network

 Target Server Type    : MySQL
 Target Server Version : 50742
 File Encoding         : 65001

 Date: 22/06/2023 00:39:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packID` int(11) NULL DEFAULT NULL,
  `unpackID` int(11) NULL DEFAULT NULL,
  UNIQUE INDEX `interface_id_IDX`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of app
-- ----------------------------
INSERT INTO `app` VALUES (1, '应用头A', '123', 3, 4);
INSERT INTO `app` VALUES (4, '应用头B', '123', 3, 4);
INSERT INTO `app` VALUES (5, '1', '1', 3, 4);
INSERT INTO `app` VALUES (6, 'test', 'test', 3, 4);
INSERT INTO `app` VALUES (7, '1', '123', 3, 4);

-- ----------------------------
-- Table structure for appdetail
-- ----------------------------
DROP TABLE IF EXISTS `appdetail`;
CREATE TABLE `appdetail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `length` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `valuestr` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `appID` int(11) NULL DEFAULT NULL,
  `optional` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  UNIQUE INDEX `interfacedetail_id_IDX`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of appdetail
-- ----------------------------
INSERT INTO `appdetail` VALUES (1, '2', '2', '1', '2', 1, NULL);
INSERT INTO `appdetail` VALUES (4, 't', 't', 't', 't', 1, '可选项（无该项内容）');
INSERT INTO `appdetail` VALUES (5, '', NULL, '', '', NULL, NULL);
INSERT INTO `appdetail` VALUES (6, '', NULL, '', '', NULL, NULL);
INSERT INTO `appdetail` VALUES (7, 'a', NULL, '', '', NULL, NULL);
INSERT INTO `appdetail` VALUES (8, 'tt', NULL, '', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for flow
-- ----------------------------
DROP TABLE IF EXISTS `flow`;
CREATE TABLE `flow`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  UNIQUE INDEX `interface_id_IDX`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flow
-- ----------------------------
INSERT INTO `flow` VALUES (1, '2122', NULL);
INSERT INTO `flow` VALUES (12, 'ttest', NULL);

-- ----------------------------
-- Table structure for flowdetail
-- ----------------------------
DROP TABLE IF EXISTS `flowdetail`;
CREATE TABLE `flowdetail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bpmnstr` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `flowID` int(11) NULL DEFAULT NULL,
  UNIQUE INDEX `interfacedetail_id_IDX`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flowdetail
-- ----------------------------
INSERT INTO `flowdetail` VALUES (9, '%3C%3Fxml%20version%3D%221.0%22%20encoding%3D%22UTF-8%22%3F%3E%0A%3Cbpmn%3Adefinitions%20xmlns%3Axsi%3D%22http%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema-instance%22%20xmlns%3Abpmn%3D%22http%3A%2F%2Fwww.omg.org%2Fspec%2FBPMN%2F20100524%2FMODEL%22%20xmlns%3Abpmndi%3D%22http%3A%2F%2Fwww.omg.org%2Fspec%2FBPMN%2F20100524%2FDI%22%20xmlns%3Adc%3D%22http%3A%2F%2Fwww.omg.org%2Fspec%2FDD%2F20100524%2FDC%22%20xmlns%3Adi%3D%22http%3A%2F%2Fwww.omg.org%2Fspec%2FDD%2F20100524%2FDI%22%20id%3D%22Definitions_0u4gsqp%22%20targetNamespace%3D%22http%3A%2F%2Fbpmn.io%2Fschema%2Fbpmn%22%20exporter%3D%22bpmn-js%20(https%3A%2F%2Fdemo.bpmn.io)%22%20exporterVersion%3D%226.1.1%22%3E%0A%20%20%3Cbpmn%3Aprocess%20id%3D%22Process_1d6bkiu%22%20isExecutable%3D%22false%22%3E%0A%20%20%20%20%3Cbpmn%3AstartEvent%20id%3D%22Event_0c4qzmi%22%20name%3D%22%E6%8E%A5%E5%8F%A3C%22%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aoutgoing%3EFlow_0ukzjgt%3C%2Fbpmn%3Aoutgoing%3E%0A%20%20%20%20%3C%2Fbpmn%3AstartEvent%3E%0A%20%20%20%20%3Cbpmn%3AsendTask%20id%3D%22Activity_06dd0zs%22%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aincoming%3EFlow_0ukzjgt%3C%2Fbpmn%3Aincoming%3E%0A%20%20%20%20%3C%2Fbpmn%3AsendTask%3E%0A%20%20%20%20%3Cbpmn%3AsequenceFlow%20id%3D%22Flow_0ukzjgt%22%20sourceRef%3D%22Event_0c4qzmi%22%20targetRef%3D%22Activity_06dd0zs%22%20%2F%3E%0A%20%20%3C%2Fbpmn%3Aprocess%3E%0A%20%20%3Cbpmndi%3ABPMNDiagram%20id%3D%22BPMNDiagram_1%22%3E%0A%20%20%20%20%3Cbpmndi%3ABPMNPlane%20id%3D%22BPMNPlane_1%22%20bpmnElement%3D%22Process_1d6bkiu%22%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNShape%20id%3D%22Event_0c4qzmi_di%22%20bpmnElement%3D%22Event_0c4qzmi%22%3E%0A%20%20%20%20%20%20%20%20%3Cdc%3ABounds%20x%3D%22240%22%20y%3D%22180%22%20width%3D%2240%22%20height%3D%2240%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cbpmndi%3ABPMNLabel%3E%0A%20%20%20%20%20%20%20%20%20%20%3Cdc%3ABounds%20x%3D%22245%22%20y%3D%22227%22%20width%3D%2230%22%20height%3D%2214%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNLabel%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNShape%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNShape%20id%3D%22Activity_06dd0zs_di%22%20bpmnElement%3D%22Activity_06dd0zs%22%3E%0A%20%20%20%20%20%20%20%20%3Cdc%3ABounds%20x%3D%22330%22%20y%3D%22160%22%20width%3D%22100%22%20height%3D%2280%22%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNShape%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNEdge%20id%3D%22Flow_0ukzjgt_di%22%20bpmnElement%3D%22Flow_0ukzjgt%22%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22280%22%20y%3D%22200%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22330%22%20y%3D%22200%22%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNEdge%3E%0A%20%20%20%20%3C%2Fbpmndi%3ABPMNPlane%3E%0A%20%20%3C%2Fbpmndi%3ABPMNDiagram%3E%0A%3C%2Fbpmn%3Adefinitions%3E%0A%7C%5B%5D%7C%5B%5D%7C%5B%7B%22id%22%3A%22Event_0c4qzmi%22%2C%22interfaceID%22%3A31%2C%22interfaceName%22%3A%22%E6%8E%A5%E5%8F%A3C%22%7D%5D%7C%5B%5D%7C%5B%5D', 1);
INSERT INTO `flowdetail` VALUES (10, '%3C%3Fxml%20version%3D%221.0%22%20encoding%3D%22UTF-8%22%3F%3E%0A%3Cbpmn%3Adefinitions%20xmlns%3Axsi%3D%22http%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema-instance%22%20xmlns%3Abpmn%3D%22http%3A%2F%2Fwww.omg.org%2Fspec%2FBPMN%2F20100524%2FMODEL%22%20xmlns%3Abpmndi%3D%22http%3A%2F%2Fwww.omg.org%2Fspec%2FBPMN%2F20100524%2FDI%22%20xmlns%3Adc%3D%22http%3A%2F%2Fwww.omg.org%2Fspec%2FDD%2F20100524%2FDC%22%20xmlns%3Adi%3D%22http%3A%2F%2Fwww.omg.org%2Fspec%2FDD%2F20100524%2FDI%22%20id%3D%22Definitions_0u4gsqp%22%20targetNamespace%3D%22http%3A%2F%2Fbpmn.io%2Fschema%2Fbpmn%22%20exporter%3D%22bpmn-js%20(https%3A%2F%2Fdemo.bpmn.io)%22%20exporterVersion%3D%226.1.1%22%3E%0A%20%20%3Cbpmn%3Aprocess%20id%3D%22Process_1d6bkiu%22%20isExecutable%3D%22false%22%3E%0A%20%20%20%20%3Cbpmn%3AstartEvent%20id%3D%22Event_0db6awt%22%20name%3D%22%E6%8E%A5%E5%8F%A3A%22%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aoutgoing%3EFlow_1wc5gdr%3C%2Fbpmn%3Aoutgoing%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aoutgoing%3EFlow_15y9g5i%3C%2Fbpmn%3Aoutgoing%3E%0A%20%20%20%20%3C%2Fbpmn%3AstartEvent%3E%0A%20%20%20%20%3Cbpmn%3AsendTask%20id%3D%22Activity_1qxiwiy%22%20name%3D%22%E8%A7%A3%E5%8C%85%E5%B0%81%E8%A3%85%E5%A4%B4A%22%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aincoming%3EFlow_1wc5gdr%3C%2Fbpmn%3Aincoming%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aoutgoing%3EFlow_1h3insv%3C%2Fbpmn%3Aoutgoing%3E%0A%20%20%20%20%3C%2Fbpmn%3AsendTask%3E%0A%20%20%20%20%3Cbpmn%3AsequenceFlow%20id%3D%22Flow_1wc5gdr%22%20sourceRef%3D%22Event_0db6awt%22%20targetRef%3D%22Activity_1qxiwiy%22%20%2F%3E%0A%20%20%20%20%3Cbpmn%3AexclusiveGateway%20id%3D%22Gateway_0icoafh%22%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aincoming%3EFlow_1h3insv%3C%2Fbpmn%3Aincoming%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aoutgoing%3EFlow_069dris%3C%2Fbpmn%3Aoutgoing%3E%0A%20%20%20%20%3C%2Fbpmn%3AexclusiveGateway%3E%0A%20%20%20%20%3Cbpmn%3AsequenceFlow%20id%3D%22Flow_1h3insv%22%20sourceRef%3D%22Activity_1qxiwiy%22%20targetRef%3D%22Gateway_0icoafh%22%20%2F%3E%0A%20%20%20%20%3Cbpmn%3AserviceTask%20id%3D%22Activity_13ccmpr%22%20name%3D%22%E8%B0%83%E7%94%A8%E8%A7%84%E5%88%99%EF%BC%9A%E6%B6%88%E6%81%AF%E4%BD%93A-%E6%B6%88%E6%81%AF%E4%BD%93B%22%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aincoming%3EFlow_069dris%3C%2Fbpmn%3Aincoming%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aoutgoing%3EFlow_1vol0eo%3C%2Fbpmn%3Aoutgoing%3E%0A%20%20%20%20%3C%2Fbpmn%3AserviceTask%3E%0A%20%20%20%20%3Cbpmn%3AsequenceFlow%20id%3D%22Flow_069dris%22%20sourceRef%3D%22Gateway_0icoafh%22%20targetRef%3D%22Activity_13ccmpr%22%20%2F%3E%0A%20%20%20%20%3Cbpmn%3AendEvent%20id%3D%22Event_1s34r9v%22%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aincoming%3EFlow_1vol0eo%3C%2Fbpmn%3Aincoming%3E%0A%20%20%20%20%3C%2Fbpmn%3AendEvent%3E%0A%20%20%20%20%3Cbpmn%3AsequenceFlow%20id%3D%22Flow_1vol0eo%22%20sourceRef%3D%22Activity_13ccmpr%22%20targetRef%3D%22Event_1s34r9v%22%20%2F%3E%0A%20%20%20%20%3Cbpmn%3AbusinessRuleTask%20id%3D%22Activity_0frpe2c%22%20name%3D%22%E5%B0%81%E8%A3%85121%22%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aincoming%3EFlow_15y9g5i%3C%2Fbpmn%3Aincoming%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aoutgoing%3EFlow_1nmljhh%3C%2Fbpmn%3Aoutgoing%3E%0A%20%20%20%20%3C%2Fbpmn%3AbusinessRuleTask%3E%0A%20%20%20%20%3Cbpmn%3AsequenceFlow%20id%3D%22Flow_15y9g5i%22%20sourceRef%3D%22Event_0db6awt%22%20targetRef%3D%22Activity_0frpe2c%22%20%2F%3E%0A%20%20%20%20%3Cbpmn%3AserviceTask%20id%3D%22Activity_1h3wevy%22%20name%3D%22%E8%B0%83%E7%94%A8%E8%A7%84%E5%88%99%EF%BC%9A%E6%B6%88%E6%81%AF%E4%BD%93A-%E6%B6%88%E6%81%AF%E4%BD%93B%22%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aincoming%3EFlow_1nmljhh%3C%2Fbpmn%3Aincoming%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aoutgoing%3EFlow_0kkt8ai%3C%2Fbpmn%3Aoutgoing%3E%0A%20%20%20%20%3C%2Fbpmn%3AserviceTask%3E%0A%20%20%20%20%3Cbpmn%3AsequenceFlow%20id%3D%22Flow_1nmljhh%22%20sourceRef%3D%22Activity_0frpe2c%22%20targetRef%3D%22Activity_1h3wevy%22%20%2F%3E%0A%20%20%20%20%3Cbpmn%3AbusinessRuleTask%20id%3D%22Activity_1fkpe37%22%20name%3D%22%E5%B0%81%E8%A3%85%E5%BA%94%E7%94%A8%E5%A4%B4A%22%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aincoming%3EFlow_0kkt8ai%3C%2Fbpmn%3Aincoming%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aoutgoing%3EFlow_16ct2id%3C%2Fbpmn%3Aoutgoing%3E%0A%20%20%20%20%3C%2Fbpmn%3AbusinessRuleTask%3E%0A%20%20%20%20%3Cbpmn%3AsequenceFlow%20id%3D%22Flow_0kkt8ai%22%20sourceRef%3D%22Activity_1h3wevy%22%20targetRef%3D%22Activity_1fkpe37%22%20%2F%3E%0A%20%20%20%20%3Cbpmn%3AexclusiveGateway%20id%3D%22Gateway_0o8p1jm%22%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aincoming%3EFlow_16ct2id%3C%2Fbpmn%3Aincoming%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aoutgoing%3EFlow_0ndhbcs%3C%2Fbpmn%3Aoutgoing%3E%0A%20%20%20%20%3C%2Fbpmn%3AexclusiveGateway%3E%0A%20%20%20%20%3Cbpmn%3AsequenceFlow%20id%3D%22Flow_16ct2id%22%20sourceRef%3D%22Activity_1fkpe37%22%20targetRef%3D%22Gateway_0o8p1jm%22%20%2F%3E%0A%20%20%20%20%3Cbpmn%3AendEvent%20id%3D%22Event_0n331lr%22%3E%0A%20%20%20%20%20%20%3Cbpmn%3Aincoming%3EFlow_0ndhbcs%3C%2Fbpmn%3Aincoming%3E%0A%20%20%20%20%3C%2Fbpmn%3AendEvent%3E%0A%20%20%20%20%3Cbpmn%3AsequenceFlow%20id%3D%22Flow_0ndhbcs%22%20name%3D%22%E9%80%89%E6%8B%A9%E8%B7%AF%E7%94%B1%EF%BC%9A%E6%8E%A5%E5%8F%A3A%2CIP%E5%9C%B0%E5%9D%80%22%20sourceRef%3D%22Gateway_0o8p1jm%22%20targetRef%3D%22Event_0n331lr%22%20%2F%3E%0A%20%20%3C%2Fbpmn%3Aprocess%3E%0A%20%20%3Cbpmndi%3ABPMNDiagram%20id%3D%22BPMNDiagram_1%22%3E%0A%20%20%20%20%3Cbpmndi%3ABPMNPlane%20id%3D%22BPMNPlane_1%22%20bpmnElement%3D%22Process_1d6bkiu%22%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNShape%20id%3D%22Event_0db6awt_di%22%20bpmnElement%3D%22Event_0db6awt%22%3E%0A%20%20%20%20%20%20%20%20%3Cdc%3ABounds%20x%3D%22140%22%20y%3D%22270%22%20width%3D%2240%22%20height%3D%2240%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cbpmndi%3ABPMNLabel%3E%0A%20%20%20%20%20%20%20%20%20%20%3Cdc%3ABounds%20x%3D%22145%22%20y%3D%22317%22%20width%3D%2230%22%20height%3D%2214%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNLabel%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNShape%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNShape%20id%3D%22Activity_1qxiwiy_di%22%20bpmnElement%3D%22Activity_1qxiwiy%22%3E%0A%20%20%20%20%20%20%20%20%3Cdc%3ABounds%20x%3D%22260%22%20y%3D%22180%22%20width%3D%22100%22%20height%3D%2280%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cbpmndi%3ABPMNLabel%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNShape%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNShape%20id%3D%22Gateway_0icoafh_di%22%20bpmnElement%3D%22Gateway_0icoafh%22%20isMarkerVisible%3D%22true%22%3E%0A%20%20%20%20%20%20%20%20%3Cdc%3ABounds%20x%3D%22415%22%20y%3D%22195%22%20width%3D%2250%22%20height%3D%2250%22%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNShape%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNShape%20id%3D%22Activity_13ccmpr_di%22%20bpmnElement%3D%22Activity_13ccmpr%22%3E%0A%20%20%20%20%20%20%20%20%3Cdc%3ABounds%20x%3D%22520%22%20y%3D%22180%22%20width%3D%22100%22%20height%3D%2280%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cbpmndi%3ABPMNLabel%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNShape%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNShape%20id%3D%22Event_1s34r9v_di%22%20bpmnElement%3D%22Event_1s34r9v%22%3E%0A%20%20%20%20%20%20%20%20%3Cdc%3ABounds%20x%3D%22682%22%20y%3D%22202%22%20width%3D%2240%22%20height%3D%2240%22%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNShape%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNShape%20id%3D%22Activity_0frpe2c_di%22%20bpmnElement%3D%22Activity_0frpe2c%22%3E%0A%20%20%20%20%20%20%20%20%3Cdc%3ABounds%20x%3D%22510%22%20y%3D%22300%22%20width%3D%22100%22%20height%3D%2280%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cbpmndi%3ABPMNLabel%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNShape%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNShape%20id%3D%22Activity_1h3wevy_di%22%20bpmnElement%3D%22Activity_1h3wevy%22%3E%0A%20%20%20%20%20%20%20%20%3Cdc%3ABounds%20x%3D%22660%22%20y%3D%22300%22%20width%3D%22100%22%20height%3D%2280%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cbpmndi%3ABPMNLabel%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNShape%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNShape%20id%3D%22Activity_1fkpe37_di%22%20bpmnElement%3D%22Activity_1fkpe37%22%3E%0A%20%20%20%20%20%20%20%20%3Cdc%3ABounds%20x%3D%22810%22%20y%3D%22300%22%20width%3D%22100%22%20height%3D%2280%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cbpmndi%3ABPMNLabel%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNShape%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNShape%20id%3D%22Gateway_0o8p1jm_di%22%20bpmnElement%3D%22Gateway_0o8p1jm%22%20isMarkerVisible%3D%22true%22%3E%0A%20%20%20%20%20%20%20%20%3Cdc%3ABounds%20x%3D%22965%22%20y%3D%22315%22%20width%3D%2250%22%20height%3D%2250%22%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNShape%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNShape%20id%3D%22Event_0n331lr_di%22%20bpmnElement%3D%22Event_0n331lr%22%3E%0A%20%20%20%20%20%20%20%20%3Cdc%3ABounds%20x%3D%221072%22%20y%3D%22322%22%20width%3D%2240%22%20height%3D%2240%22%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNShape%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNEdge%20id%3D%22Flow_1wc5gdr_di%22%20bpmnElement%3D%22Flow_1wc5gdr%22%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22180%22%20y%3D%22290%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22220%22%20y%3D%22290%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22220%22%20y%3D%22220%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22260%22%20y%3D%22220%22%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNEdge%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNEdge%20id%3D%22Flow_1h3insv_di%22%20bpmnElement%3D%22Flow_1h3insv%22%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22360%22%20y%3D%22220%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22415%22%20y%3D%22220%22%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNEdge%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNEdge%20id%3D%22Flow_069dris_di%22%20bpmnElement%3D%22Flow_069dris%22%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22465%22%20y%3D%22220%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22520%22%20y%3D%22220%22%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNEdge%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNEdge%20id%3D%22Flow_1vol0eo_di%22%20bpmnElement%3D%22Flow_1vol0eo%22%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22620%22%20y%3D%22220%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22650%22%20y%3D%22220%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22650%22%20y%3D%22222%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22682%22%20y%3D%22222%22%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNEdge%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNEdge%20id%3D%22Flow_15y9g5i_di%22%20bpmnElement%3D%22Flow_15y9g5i%22%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22180%22%20y%3D%22290%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22345%22%20y%3D%22290%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22345%22%20y%3D%22340%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22510%22%20y%3D%22340%22%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNEdge%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNEdge%20id%3D%22Flow_1nmljhh_di%22%20bpmnElement%3D%22Flow_1nmljhh%22%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22610%22%20y%3D%22340%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22660%22%20y%3D%22340%22%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNEdge%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNEdge%20id%3D%22Flow_0kkt8ai_di%22%20bpmnElement%3D%22Flow_0kkt8ai%22%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22760%22%20y%3D%22340%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22810%22%20y%3D%22340%22%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNEdge%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNEdge%20id%3D%22Flow_16ct2id_di%22%20bpmnElement%3D%22Flow_16ct2id%22%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22910%22%20y%3D%22340%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%22965%22%20y%3D%22340%22%20%2F%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNEdge%3E%0A%20%20%20%20%20%20%3Cbpmndi%3ABPMNEdge%20id%3D%22Flow_0ndhbcs_di%22%20bpmnElement%3D%22Flow_0ndhbcs%22%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%221015%22%20y%3D%22340%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%221040%22%20y%3D%22340%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%221040%22%20y%3D%22342%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cdi%3Awaypoint%20x%3D%221072%22%20y%3D%22342%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3Cbpmndi%3ABPMNLabel%3E%0A%20%20%20%20%20%20%20%20%20%20%3Cdc%3ABounds%20x%3D%221016%22%20y%3D%22296%22%20width%3D%2288%22%20height%3D%2227%22%20%2F%3E%0A%20%20%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNLabel%3E%0A%20%20%20%20%20%20%3C%2Fbpmndi%3ABPMNEdge%3E%0A%20%20%20%20%3C%2Fbpmndi%3ABPMNPlane%3E%0A%20%20%3C%2Fbpmndi%3ABPMNDiagram%3E%0A%3C%2Fbpmn%3Adefinitions%3E%0A%7C%5B%7B%22id%22%3A%22Activity_0frpe2c%22%2C%22appID%22%3A4%2C%22appName%22%3A%22121%22%2C%22rulestr%22%3A%22%E5%B0%81%E8%A3%85%22%7D%2C%7B%22id%22%3A%22Activity_1fkpe37%22%2C%22appID%22%3A1%2C%22appName%22%3A%22%E5%BA%94%E7%94%A8%E5%A4%B4A%22%2C%22rulestr%22%3A%22%E5%B0%81%E8%A3%85%22%7D%5D%7C%5B%7B%22id%22%3A%22Activity_13ccmpr%22%2C%22source%22%3A%22%22%2C%22rulestr%22%3A%22%E6%B6%88%E6%81%AF%E4%BD%93%E8%BD%AC%E5%8C%96%E6%B5%81%E7%A8%8B1%22%2C%22ruleID%22%3A22%2C%22ruleName%22%3A%22%E6%B6%88%E6%81%AF%E4%BD%93A-%E6%B6%88%E6%81%AF%E4%BD%93B%22%7D%2C%7B%22id%22%3A%22Activity_1h3wevy%22%2C%22ruleID%22%3A22%2C%22ruleName%22%3A%22%E6%B6%88%E6%81%AF%E4%BD%93A-%E6%B6%88%E6%81%AF%E4%BD%93B%22%7D%5D%7C%5B%7B%22id%22%3A%22Event_0db6awt%22%2C%22interfaceID%22%3A24%2C%22interfaceName%22%3A%22%E6%8E%A5%E5%8F%A3A%22%7D%5D%7C%5B%7B%22id%22%3A%22Activity_1qxiwiy%22%2C%22packageID%22%3A6%2C%22packageName%22%3A%22%E5%B0%81%E8%A3%85%E5%A4%B4A%22%2C%22rulestr%22%3A%22%E8%A7%A3%E5%8C%85%22%7D%5D%7C%5B%7B%22id%22%3A%22Flow_0ndhbcs%22%2C%22source%22%3A%5B%5B%22%E6%8E%A5%E5%8F%A3A%22%2C%22IP%E5%9C%B0%E5%9D%80%22%5D%5D%2C%22rulestr%22%3A%22%E6%8E%A5%E5%8F%A3A.%E7%B1%BB%E5%9E%8B%5Cn%E6%8E%A5%E5%8F%A3A.IP%E5%9C%B0%E5%9D%80%5Cn%22%7D%5D', 12);

-- ----------------------------
-- Table structure for func
-- ----------------------------
DROP TABLE IF EXISTS `func`;
CREATE TABLE `func`  (
  `descri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hasChildren` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  UNIQUE INDEX `interface_id_IDX`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of func
-- ----------------------------
INSERT INTO `func` VALUES ('三角正弦函数1', 1, 'sin(x)1', '逻辑函数', 'false');
INSERT INTO `func` VALUES ('余弦三角函数', 2, 'cos(x)', '数学函数', 'false');
INSERT INTO `func` VALUES ('三角正切函数', 3, 'tan(x)', '数学函数', 'false');
INSERT INTO `func` VALUES ('去除空格', 4, 'trim', '字符串函数', 'false');
INSERT INTO `func` VALUES ('或的逻辑运算', 5, '||', '逻辑函数', 'false');
INSERT INTO `func` VALUES ('现在', 6, 'now', '时间函数', 'false');
INSERT INTO `func` VALUES ('集合尾部增加元素', 7, 'push', '集合函数', 'false');

-- ----------------------------
-- Table structure for interface
-- ----------------------------
DROP TABLE IF EXISTS `interface`;
CREATE TABLE `interface`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  UNIQUE INDEX `interface_id_IDX`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of interface
-- ----------------------------
INSERT INTO `interface` VALUES (24, '接口A', '网口');
INSERT INTO `interface` VALUES (25, '接口B', '串口');
INSERT INTO `interface` VALUES (31, '接口C', '网口');

-- ----------------------------
-- Table structure for interfacedetail
-- ----------------------------
DROP TABLE IF EXISTS `interfacedetail`;
CREATE TABLE `interfacedetail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `length` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `valuestr` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `interfaceID` int(11) NULL DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  UNIQUE INDEX `interfacedetail_id_IDX`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of interfacedetail
-- ----------------------------
INSERT INTO `interfacedetail` VALUES (81, '类型', 'type', '0', '网口', 24, NULL);
INSERT INTO `interfacedetail` VALUES (82, 'IP地址', 'IPAddress', '32', '', 24, NULL);
INSERT INTO `interfacedetail` VALUES (85, '类型', 'type', '0', '串口', 25, NULL);
INSERT INTO `interfacedetail` VALUES (86, '出口', 'outside', '1', '', 25, NULL);
INSERT INTO `interfacedetail` VALUES (87, '比特率', 'bitrate', '8', '', 25, NULL);
INSERT INTO `interfacedetail` VALUES (97, '类型', 'type', '0', '网口', 31, NULL);
INSERT INTO `interfacedetail` VALUES (98, 'IP地址', 'IPAddress', '32', '', 31, NULL);
INSERT INTO `interfacedetail` VALUES (100, '12', '31', '312', '1', NULL, NULL);
INSERT INTO `interfacedetail` VALUES (101, '1', '1', '1', '1', NULL, NULL);
INSERT INTO `interfacedetail` VALUES (102, 'tt', 'tt', '', 'tt', 25, NULL);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  UNIQUE INDEX `interface_id_IDX`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '消息体A', '321');
INSERT INTO `message` VALUES (3, '消息体B', '321');
INSERT INTO `message` VALUES (4, 'cd', '321');
INSERT INTO `message` VALUES (5, '123', '1321');
INSERT INTO `message` VALUES (6, '测试', '321');

-- ----------------------------
-- Table structure for messagedetail
-- ----------------------------
DROP TABLE IF EXISTS `messagedetail`;
CREATE TABLE `messagedetail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `length` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `valuestr` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `messageID` int(11) NULL DEFAULT NULL,
  `optional` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pid` int(11) NULL DEFAULT NULL,
  UNIQUE INDEX `interfacedetail_id_IDX`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of messagedetail
-- ----------------------------
INSERT INTO `messagedetail` VALUES (2, 'arra1', 'arra1', '2', '2', 1, '可选项（无该项内容）', '基础属性', 0);
INSERT INTO `messagedetail` VALUES (4, 'group2', 'group2', '', '', 1, '可选项（必须有该项内容）', '嵌套消息体', 0);
INSERT INTO `messagedetail` VALUES (5, 'arra3', 'arra3', '3', '3', 1, '可选项（无该项内容）', '基础属性', 0);
INSERT INTO `messagedetail` VALUES (7, '123', '321', '', '', 0, '必选项', '嵌套消息体', 4);
INSERT INTO `messagedetail` VALUES (8, '12', '31', '', '', 0, '必选项', '嵌套消息体', 4);
INSERT INTO `messagedetail` VALUES (9, 'abc', 'bcd', '', '', 1, '必选项', '嵌套消息体', 4);
INSERT INTO `messagedetail` VALUES (11, 'test', 'test', '', '', 1, '可选项（无该项内容）', '嵌套消息体', 4);
INSERT INTO `messagedetail` VALUES (12, '11', '11', '11', '11', 1, '可选项（无该项内容）', '基础属性', 11);
INSERT INTO `messagedetail` VALUES (13, 'as', 'as', 'asdf', 'asdf', 1, '可选项（无该项内容）', '基础属性', 11);
INSERT INTO `messagedetail` VALUES (14, 'sf', 'asf', '', '', 1, '可选项（无该项内容）', '嵌套消息体', 9);
INSERT INTO `messagedetail` VALUES (15, '2', '2', '2', '2', 6, '可选项（无该项内容）', '基础属性', 0);
INSERT INTO `messagedetail` VALUES (16, '1', '1', '', '', 6, '可选项（必须有该项内容）', '嵌套消息体', 0);
INSERT INTO `messagedetail` VALUES (17, '3', '3', '3', '3', 6, '可选项（无该项内容）', '基础属性', 0);
INSERT INTO `messagedetail` VALUES (18, 'abc', 'bcd', '', '', 6, '必选项', '嵌套消息体', 4);
INSERT INTO `messagedetail` VALUES (19, 'test', 'test', '', '', 6, '可选项（无该项内容）', '嵌套消息体', 4);
INSERT INTO `messagedetail` VALUES (20, '11', '11', '11', '11', 6, '可选项（无该项内容）', '基础属性', 11);
INSERT INTO `messagedetail` VALUES (21, 'as', 'as', 'asdf', 'asdf', 6, '可选项（无该项内容）', '基础属性', 11);
INSERT INTO `messagedetail` VALUES (22, 'sf', 'asf', '', '', 6, '可选项（无该项内容）', '嵌套消息体', 9);
INSERT INTO `messagedetail` VALUES (30, '测试属性B1', 'arrb1', '', '', 3, '', '基础属性', 0);
INSERT INTO `messagedetail` VALUES (31, '测试属性B3', 'arrb2', '', '', 3, '', '基础属性', 0);

-- ----------------------------
-- Table structure for pack
-- ----------------------------
DROP TABLE IF EXISTS `pack`;
CREATE TABLE `pack`  (
  `calladdr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  UNIQUE INDEX `interface_id_IDX`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pack
-- ----------------------------
INSERT INTO `pack` VALUES ('AppUnpack', 2, 'AppUnpack', '应用头解包');
INSERT INTO `pack` VALUES ('PackagePack', 3, 'PackagePack', '封装头打包');
INSERT INTO `pack` VALUES ('PackageUnpack', 4, 'PackageUnpack', '封装头解包');
INSERT INTO `pack` VALUES ('AppPack', 5, 'AppPack', '应用头打包');

-- ----------------------------
-- Table structure for package
-- ----------------------------
DROP TABLE IF EXISTS `package`;
CREATE TABLE `package`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packID` int(11) NULL DEFAULT NULL,
  `unpackID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `package_id_IDX`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of package
-- ----------------------------
INSERT INTO `package` VALUES (6, '封装头A', 'tt', 3, 4);
INSERT INTO `package` VALUES (7, '封装头B', 'tt', 3, 4);
INSERT INTO `package` VALUES (8, '1', '31', 3, 4);
INSERT INTO `package` VALUES (9, '123', '', 3, 4);
INSERT INTO `package` VALUES (10, 'test', 'test', NULL, NULL);

-- ----------------------------
-- Table structure for packagedetail
-- ----------------------------
DROP TABLE IF EXISTS `packagedetail`;
CREATE TABLE `packagedetail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `length` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `valuestr` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packageID` int(11) NULL DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  UNIQUE INDEX `interfacedetail_id_IDX`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of packagedetail
-- ----------------------------
INSERT INTO `packagedetail` VALUES (1, 'tt', 'tt', '', 'tt', NULL, NULL);
INSERT INTO `packagedetail` VALUES (2, '1', '1', '', '1', NULL, NULL);
INSERT INTO `packagedetail` VALUES (3, '1', '1', '', '1', NULL, NULL);
INSERT INTO `packagedetail` VALUES (7, 'sf', 'sd', '', 'sd', 6, NULL);
INSERT INTO `packagedetail` VALUES (9, 'sf', 'sd', '', 'sd', 7, NULL);
INSERT INTO `packagedetail` VALUES (10, 'fas', 'sfd', '', 'sa', 7, NULL);
INSERT INTO `packagedetail` VALUES (12, 'tt', 'tt', '', 'tt', 6, NULL);

-- ----------------------------
-- Table structure for rule
-- ----------------------------
DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sourceid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `targetid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  UNIQUE INDEX `interface_id_IDX`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rule
-- ----------------------------
INSERT INTO `rule` VALUES (1, '', '', NULL, NULL);
INSERT INTO `rule` VALUES (2, '123', '', NULL, NULL);
INSERT INTO `rule` VALUES (3, '2', '', NULL, NULL);
INSERT INTO `rule` VALUES (4, '1', '', NULL, NULL);
INSERT INTO `rule` VALUES (5, '2', '', NULL, NULL);
INSERT INTO `rule` VALUES (8, '123', '', NULL, NULL);
INSERT INTO `rule` VALUES (10, '1', '', NULL, NULL);
INSERT INTO `rule` VALUES (13, '321', '', NULL, NULL);
INSERT INTO `rule` VALUES (14, 'test12', '', '1', NULL);
INSERT INTO `rule` VALUES (15, 'test_复制', '', NULL, NULL);
INSERT INTO `rule` VALUES (17, '312', '', NULL, '5');
INSERT INTO `rule` VALUES (18, 'TEST', '', '4', NULL);
INSERT INTO `rule` VALUES (19, '1', '', NULL, '3');
INSERT INTO `rule` VALUES (20, '12', '', NULL, NULL);
INSERT INTO `rule` VALUES (21, '2', '', NULL, NULL);
INSERT INTO `rule` VALUES (22, '消息体A-消息体B', '', '1', '3');
INSERT INTO `rule` VALUES (24, '测试', '', '1', '3');

-- ----------------------------
-- Table structure for ruledetail
-- ----------------------------
DROP TABLE IF EXISTS `ruledetail`;
CREATE TABLE `ruledetail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `length` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rulestr` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ruleID` int(11) NULL DEFAULT NULL,
  `optional` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sourceid` int(11) NULL DEFAULT NULL,
  `targetid` int(11) NULL DEFAULT NULL,
  `sourcedata` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `targetdata` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `funcrule` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  UNIQUE INDEX `interfacedetail_id_IDX`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ruledetail
-- ----------------------------
INSERT INTO `ruledetail` VALUES (1, 't1', NULL, '', '消息体A.arra1\n消息体B.arrb1\n', 22, NULL, 1, 3, '[[\"arra1\"]]', '[[\"arrb1\"]]', '内置转换公式', 'cos(x)');
INSERT INTO `ruledetail` VALUES (2, 'test', NULL, '', '消息体A.arra1\n消息体A.arra1\n消息体A.group2.bcd.asf\n消息体B.arrb2\n', 22, NULL, 1, 3, '[[\"arra1\"],[\"group2\",\"bcd\",\"asf\"]]', '[[\"arrb2\"]]', '自定义转换公式', NULL);
INSERT INTO `ruledetail` VALUES (3, 'cc', NULL, '', '消息体A.arra1\n消息体B.arrb2\n', 22, NULL, 1, 3, '[[\"arra1\"]]', '[[\"arrb2\"]]', '内置转换公式', NULL);
INSERT INTO `ruledetail` VALUES (6, '自定义', NULL, '', '消息体A.arra1\n消息体A.arra1\n消息体A.group2.test.as\n消息体B.arrb2\n', 22, NULL, 1, 3, '[[\"arra1\"],[\"group2\",\"test\",\"as\"]]', '[[\"arrb2\"]]', '自定义转换公式', NULL);
INSERT INTO `ruledetail` VALUES (7, '函数测试', NULL, '', '消息体A.arra1\n消息体B.arrb2\n', 22, NULL, 1, 3, '[[\"arra1\"]]', '[[\"arrb2\"]]', '内置转换公式', NULL);
INSERT INTO `ruledetail` VALUES (8, 'test', NULL, '', '消息体A.group2.bcd.asf\n消息体B.arrb1\n', 22, NULL, 1, 3, '[[\"group2\",\"bcd\",\"asf\"]]', '[[\"arrb1\"]]', '内置转换公式', 'sin(x)1');

SET FOREIGN_KEY_CHECKS = 1;
