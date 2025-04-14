CREATE TABLE public.users (
  id serial NOT NULL PRIMARY KEY,
  email text NOT NULL UNIQUE,
  password text NOT NULL,
  session_id text UNIQUE
);

CREATE INDEX idx_session_id ON public.users(session_id);