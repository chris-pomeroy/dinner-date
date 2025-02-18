CREATE TABLE
  public.ingredients (
    id serial NOT NULL PRIMARY KEY,
    recipe_id serial references recipes(id),
    ingredient text NOT NULL
  );
