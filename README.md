# Simple Game Engine Examples

Here lies a Simple Game Engine. 

And then examples using the Simple Game Engine for Android using OpenGL. Please note that this is the first time ive ever done A) android, B)openGL, C) game development.....which means that things are probably done wierd / wrong. 

Im pretty much done with the experimental project, but it ended up being kinda cool...here are some links to YouTube vidoes running the examples in this project.

[Video 1: Shapes, Tweens, and Camera Direction](https://www.youtube.com/watch?v=LUiqlVIovpU)

<a href="http://www.youtube.com/watch?feature=player_embedded&v=LUiqlVIovpU" target="_blank"><img src="https://i.ytimg.com/vi/LUiqlVIovpU/3.jpg?time=1471054888665" 
alt="IMAGE ALT TEXT HERE" width="240" height="180" border="10" /></a>

[Vidoe 2: Infrastructure](https://www.youtube.com/watch?v=3QSwVIf4KPg)

<a href="http://www.youtube.com/watch?feature=player_embedded&v=3QSwVIf4KPg" target="_blank"><img src="https://i.ytimg.com/vi/3QSwVIf4KPg/2.jpg?time=1471054861901"
alt="IMAGE ALT TEXT HERE" width="240" height="180" border="10" /></a>

[Vidoe 3: A First Person Level](https://youtu.be/1t-nn1Z-NuE)

<a href="http://www.youtube.com/watch?feature=player_embedded&v=1t-nn1Z-NuE" target="_blank"><img src="https://i.ytimg.com/vi/1t-nn1Z-NuE/2.jpg?time=1471054737779" 
alt="IMAGE ALT TEXT HERE" width="240" height="180" border="10" /></a>

The point of the engine is to give builder style commands for
   - adding objects
   - movement / tweening / interpolation
   - camera
   - physics

For Video number 2 above, here is the code that generated that scene...where you see SGE, thats the code accessing the Simple Game Engine. 

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * On Frame
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void onFrame() {

        //Part 1...
        //Show How the Walls/Floor Work
        if(SGE.properties().totalFrames() == 1){
            SGE.camera().changeDepthFocus(1f, 30f);
        }
        if (SGE.properties().totalFrames() == 2) {
            floor = SGE.construct().infrastructure().floor().leftX(-21f).rightX(21f).nearZ(21f).farZ(-21f).y(0).color(new SimpleColor(Color.CYAN)).build();
        }
        if (SGE.properties().totalFrames() == 3) {
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(5/speed).toX(0).toY(28).toZ(60).build());
        }
        if (SGE.properties().totalFrames() == 50) {
            wall = SGE.construct().infrastructure().wall().startX(-3f).endX(3f).startZ(0f).endZ(0f).height(8f).thickness(1).color(new SimpleColor(Color.RED)).build();
        }
        if(SGE.properties().totalFrames() == 150){
            SGE.contents().remove(floor);
            SGE.contents().remove(wall);
        }
        if(SGE.properties().totalFrames() == 151){

            floor = SGE.construct().infrastructure().floor().leftX(-20f).rightX(20f).nearZ(20f).farZ(-20f).y(0).textureId(Setup_Textures.texture_birchwood).build();
            wall = SGE.construct().infrastructure().wall().startX(-3f).endX(3f).startZ(0f).endZ(0f).height(8f).thickness(1).textureId(Setup_Textures.texture_brickwall).build();
        }
        if(SGE.properties().totalFrames() == 275) {
            SGE.contents().remove(wall);
        }

        //Part 2...
        //Start Adding the Walls 1 by 1
        if(SGE.properties().totalFrames() == 300) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                                .startX(-18f).endX(-18f).startZ(-9f).endZ(-18f).build();
            mazeWalls.add(w);
        }
        if(SGE.properties().totalFrames() == 330) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                               .startX(-18f).endX(-13.7f).startZ(-9f).endZ(-9f).build();
            mazeWalls.add(w);
        }
        if(SGE.properties().totalFrames() == 360) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                    .startX(-10f).endX(-10f).startZ(-3f).endZ(-18f).build();
            mazeWalls.add(w);
        }
        if(SGE.properties().totalFrames() == 390) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                    .startX(-15f).endX(-10f).startZ(-3f).endZ(-3f).build();
            mazeWalls.add(w);
        }
        if(SGE.properties().totalFrames() == 420) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                    .startX(-18f).endX(-18f).startZ(-9f).endZ(18f).build();
            mazeWalls.add(w);
        }
        if(SGE.properties().totalFrames() == 450) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                    .startX(-18f).endX(-13.7f).startZ(5f).endZ(5f).build();
            mazeWalls.add(w);
        }
        if(SGE.properties().totalFrames() == 480) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                    .startX(-11f).endX(-7f).startZ(5f).endZ(5f).build();
            mazeWalls.add(w);
        }
        if(SGE.properties().totalFrames() == 510) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                    .startX(-1f).endX(5f).startZ(5f).endZ(5f).build();
            mazeWalls.add(w);
        }
        //angled wall ... no problem
        if(SGE.properties().totalFrames() == 540) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                    .startX(-7f).endX(-1f).startZ(5f).endZ(14f).build();
            mazeWalls.add(w);
        }
        if(SGE.properties().totalFrames() == 570) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                    .startX(-1f).endX(6f).startZ(14f).endZ(14f).build();
            mazeWalls.add(w);
        }

        //lets start moving around
        if(SGE.properties().totalFrames() == 425) {
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(15).toX(0).toY(64).toZ(3).build());
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(5).toX(0).toY(28).toZ(60).build());
            SGE.director().queueDirector(DIR_Orbit.builder().duration(8500/speed).secondsPerRevolution(15).build());
        }


        //Part 3...
        //Add a Wall Sequence
        if(SGE.properties().totalFrames() == 630){

            ArrayList<Vertex> wallSequence = new ArrayList<>();

            //border
            wallSequence.add(new SimpleVertex(-10f, 0f, -18f));
            wallSequence.add(new SimpleVertex(  6f, 0f, -18f));
            wallSequence.add(new SimpleVertex(  6f, 0f, -18f));
            wallSequence.add(new SimpleVertex( 18f, 0f, -18f));

            wallSequence.add(new SimpleVertex( 18f, 0f, -18f));
            wallSequence.add(new SimpleVertex( 18f, 0f, -12f));
            wallSequence.add(new SimpleVertex( 18f, 0f, -12f));
            wallSequence.add(new SimpleVertex( 18f, 0f,  -6f));
            wallSequence.add(new SimpleVertex( 18f, 0f,  -6f));
            wallSequence.add(new SimpleVertex( 18f, 0f,   0f));
            wallSequence.add(new SimpleVertex( 18f, 0f,   0f));
            wallSequence.add(new SimpleVertex( 18f, 0f,   6f));
            wallSequence.add(new SimpleVertex( 18f, 0f,   6f));
            wallSequence.add(new SimpleVertex( 18f, 0f,   12f));
            wallSequence.add(new SimpleVertex( 18f, 0f,   12f));
            wallSequence.add(new SimpleVertex( 18f, 0f,   18f));

            wallSequence.add(new SimpleVertex( 18f, 0f,  18f));
            wallSequence.add(new SimpleVertex(-18f, 0f,  18f));

            //some more inner walls
            wallSequence.add(new SimpleVertex( -4f, 0f,   1f));
            wallSequence.add(new SimpleVertex( -4f, 0f,  -6f));
            wallSequence.add(new SimpleVertex( -4f, 0f,  -10f));
            wallSequence.add(new SimpleVertex( -4f, 0f,  -14f));

            List<Integer> seqWalls = SGE.construct().infrastructure().wall_sequence().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                                        .pushSequence(wallSequence).build();
            mazeWalls.addAll(seqWalls);
        }


        //Part 4...
        //Dance and Play
        if(SGE.properties().totalFrames() == 720){

            Map<String, Object> props = new HashMap<>();
            props.put(SGE.CONTENT_TEXTURE_ID, Setup_Textures.texture_cartoonBrickwall);

            for(int mazeWallId : mazeWalls){
                SGE.contents().get(mazeWallId).applyProperties(props);
            }
        }
        if(SGE.properties().totalFrames() == 1000){

            Map<String, Object> props = new HashMap<>();
            props.put(SGE.CONTENT_TEXTURE_ID, Setup_Textures.texture_orangeShine);

            for(int mazeWallId : mazeWalls){
                SGE.contents().get(mazeWallId).applyProperties(props);
            }
        }
        if(SGE.properties().totalFrames() == 1200){

            Map<String, Object> props = new HashMap<>();
            props.put(SGE.CONTENT_TEXTURE_ID, Setup_Textures.texture_metalPanel);

            SGE.contents().get(floor).applyProperties(props);
        }
        if(SGE.properties().totalFrames() == 1400){

            SGE.director().killAllDirectors();
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(5/speed).toX(0).toY(28).toZ(60).build());
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(7/speed).toX(0).toY(64).toZ(19).build());
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(3/speed).toX(16f).toY(1.7f).toZ(-16).build());
            SGE.director().queueDirector(DIR_StillShot.builder().duration(3/speed).build());
            SGE.director().queueDirector(DIR_PanLeftRight.builder().duration(4.8f/speed).left(Constants.PI/3.2f).build());
            SGE.director().queueDirector(DIR_PanLeftRight.builder().duration(8.5f/speed).right(Constants.PI/1.9f).build());
        }
        if(SGE.properties().totalFrames() == 1600){

            Map<String, Object> props = new HashMap<>();
            props.put(SGE.CONTENT_TEXTURE_ID, Setup_Textures.texture_birchwood);

            SGE.contents().get(floor).applyProperties(props);
        }
    }

  
