CREATE TABLE public.users (
  id serial NOT NULL PRIMARY KEY,
  first_name text NOT NULL,
  email text NOT NULL UNIQUE,
  password_hash text NOT NULL,
  session_id text UNIQUE,
  current_lobby_id INTEGER REFERENCES lobbies(id)
);

CREATE INDEX idx_session_id ON public.users(session_id);