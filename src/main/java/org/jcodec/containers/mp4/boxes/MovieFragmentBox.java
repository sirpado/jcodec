package org.jcodec.containers.mp4.boxes;

import java.util.List;

/**
 * This class is part of JCodec ( www.jcodec.org ) This software is distributed
 * under FreeBSD License
 * 
 * Movie fragment box and dedicated routines
 * 
 * @author The JCodec project
 * 
 */
public class MovieFragmentBox extends NodeBox {

    private MovieBox moov;

    public MovieFragmentBox() {
        super(new Header(fourcc()));
    }

    public static String fourcc() {
        return "moof";
    }

    public MovieBox getMovie() {
        return moov;
    }

    public void setMovie(MovieBox moov) {
        this.moov = moov;
    }

    protected void getModelFields(List<String> model) {

    }

    public TrackFragmentBox[] getTracks() {
        return Box.findAll(this, TrackFragmentBox.class, TrackFragmentBox.fourcc());
    }

    public int getSequenceNumber() {
        MovieFragmentHeaderBox mfhd = Box
                .findFirst(this, MovieFragmentHeaderBox.class, MovieFragmentHeaderBox.fourcc());
        if (mfhd == null)
            throw new RuntimeException("Corrupt movie fragment, no header atom found");
        return mfhd.getSequenceNumber();
    }
}
