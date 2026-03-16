CREATE TABLE users (
    user_id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    name TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
);

CREATE TABLE user_session (
    session_id TEXT PRIMARY KEY,
    user_id UUID,
    CONSTRAINT fk_user_session_user
        FOREIGN KEY (user_id)
        REFERENCES users(user_id)
);

CREATE TABLE deck (
    deck_id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT DEFAULT NULL,
    user_id UUID,
    CONSTRAINT fk_deck_user
        FOREIGN KEY (user_id)
        REFERENCES users(user_id)
);

CREATE TABLE card (
    card_id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    question TEXT NOT NULL,
    answer TEXT NOT NULL,
    user_id UUID,
    deck_id UUID,
    CONSTRAINT fk_card_user
        FOREIGN KEY (user_id)
        REFERENCES users(user_id),
    CONSTRAINT fk_card_deck
        FOREIGN KEY (deck_id)
        REFERENCES deck(deck_id)
        ON DELETE CASCADE
);