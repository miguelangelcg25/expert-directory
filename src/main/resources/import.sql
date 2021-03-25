INSERT INTO `trajectdb`.`user` (`id`, `name`) VALUES ('1', 'Miguelangel');
INSERT INTO `trajectdb`.`user` (`id`, `name`) VALUES ('2', 'Angelica');

INSERT INTO `trajectdb`.`user_friend` (`user_id`, `friend_id`) VALUES ('1', '2');
INSERT INTO `trajectdb`.`user_friend` (`user_id`, `friend_id`) VALUES ('2', '1');

INSERT INTO `trajectdb`.`topic` (`id`, `name`) VALUES ('1', 'Java');

INSERT INTO `trajectdb`.`user_topic` (`user_id`, `topic_id`) VALUES ('1', '1');
INSERT INTO `trajectdb`.`user_topic` (`user_id`, `topic_id`) VALUES ('2', '1');