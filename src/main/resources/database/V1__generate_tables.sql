CREATE TABLE player (
    player_id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username    VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE game (
    game_id     BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT now(),
    player_one  BIGINT NOT NULL REFERENCES player (player_id) ON DELETE RESTRICT,
    player_two  BIGINT REFERENCES player (player_id) ON DELETE RESTRICT,
    score_one   DECIMAL NOT NULL DEFAULT 0.0,
    score_two   DECIMAL,
    finished    BOOLEAN NOT NULL DEFAULT FALSE
);
CREATE TABLE question (
    question_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    question    VARCHAR(500) NOT NULL,
    duration    INTEGER NOT NULL DEFAULT 15
);

CREATE TABLE game_question (
    game_id     BIGINT NOT NULL REFERENCES game (game_id) ON DELETE CASCADE,
    question_id BIGINT NOT NULL REFERENCES question (question_id) ON DELETE RESTRICT,
    position    INT NOT NULL,
    PRIMARY KEY  (game_id, question_id),
    UNIQUE  (game_id, position)
);

CREATE TABLE answer (
    answer_id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    question_id BIGINT NOT NULL REFERENCES question (question_id) ON DELETE CASCADE,
    content     VARCHAR(500) NOT NULL,
    is_correct  BOOLEAN NOT NULL DEFAULT FALSE,
    UNIQUE (question_id, answer_id)
);

CREATE TABLE game_player_answer (
    game_id     BIGINT NOT NULL,
    player_id   BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    answer_id   BIGINT,
    response_ms INTEGER,
    points      INTEGER NOT NULL DEFAULT 0,
    is_correct  BOOLEAN,

    answered_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    PRIMARY KEY (game_id, player_id, question_id),

    FOREIGN KEY (game_id, question_id)
        REFERENCES game_question (game_id, question_id)
        ON DELETE CASCADE,

    FOREIGN KEY (game_id)
        REFERENCES game (game_id) ON DELETE CASCADE,

    FOREIGN KEY (player_id)
        REFERENCES player (player_id) ON DELETE CASCADE,

    FOREIGN KEY (question_id, answer_id)
        REFERENCES answer (question_id, answer_id)
        ON DELETE RESTRICT,

    CONSTRAINT chk_no_answer_points
        CHECK (
            (answer_id IS NULL AND points = 0 AND is_correct IS NULL)
                OR
            (answer_id IS NOT NULL AND is_correct IS NOT NULL)
            ),

    CONSTRAINT chk_response_ms
        CHECK (response_ms IS NULL OR response_ms >= 0)
);