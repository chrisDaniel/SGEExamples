package com.cdaniel.simplegameengine.core;

/**
 * Created by christopher.daniel on 5/5/16.
 */
public interface SimpleGameEngine {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Context
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    String SGE_PIXEL_WIDTH  = "Width of SGE Display (in pixels)";
    String SGE_PIXEL_HEIGHT = "Height of SGE Display (in pixels)";




    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Content
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    String CONTENT_NAME = "Just an Optional name if you like";
    String CONTENT_GROUPING = "A user defined String value to group content types";
    String CONTENT_GROUPING_Default = "Just in standard default bucket...where everybody goes unless specifically defined";

    String CONTENT_STATE = "The content's current State of Interactability(?)";
    String CONTENT_STATE_Active   = "The Content is current active and included in all calculations - Default Behaviour";
    String CONTENT_STATE_Inactive = "We will keep the content around, however it is not considered active";
    String CONTENT_STATE_Dead     = "The Content is now scheduled for removal";

    String CONTENT_VIZ = "Is the content going to be drawn?";
    String CONTENT_VIZ_No  = "The Content is invisible (but may still be active) - Default Behaviour";
    String CONTENT_VIZ_Yes = "The Content is visible and will be rendered when inside the camera view";

    String CONTENT_TEXTURE_ID = "Id for the Texture";
    String CONTENT_COLOR = "Instance from the Color Interface";




    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Camera
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    String CAMERA_POSITION_INITIAL              = "The Camera will be located down the -Z axis looking at the origin. The default starting position";
    String CAMERA_POSITION_FPS_CONTENT          = "The Camera will be placed at the perspective of a selected Content (1st Person View)";
    String CAMERA_POSITION_BEHIND_CONTENT       = "The Camera will follow behind a selected Content";
    String CAMERA_POSITION_ABOVE_CONTENT        = "The Camera will be above and focused on a selected Content";
    String CAMERA_POSITION_FREE                 = "The Camera will be uncoupled to any specific content";
}
