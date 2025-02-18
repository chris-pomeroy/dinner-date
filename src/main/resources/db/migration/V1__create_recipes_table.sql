CREATE TABLE
  public.recipes (
    id serial NOT NULL PRIMARY KEY,
    title text NOT NULL,
    instructions text NOT NULL,
    image_name text NULL
  );