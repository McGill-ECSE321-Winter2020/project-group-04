package ca.mcgill.ecse321.petadoptionsystem.dto;

public class UploadImageDTO {
    private String imageName;
    private String imageDownloadUri;
    private String imageType;
    private long size;

    /**
     *
     * @param imageName
     * @param imageDownloadUri
     * @param imageType
     * @param size
     */
    public UploadImageDTO(String imageName, String imageDownloadUri, String imageType, long size) {
        this.imageName = imageName;
        this.imageDownloadUri = imageDownloadUri;
        this.imageType = imageType;
        this.size = size;
    }

    public String getimageName() {
        return imageName;
    }

    public void setimageName(String imageName) {
        this.imageName = imageName;
    }

    public String getimageDownloadUri() {
        return imageDownloadUri;
    }

    public void setimageDownloadUri(String imageDownloadUri) {
        this.imageDownloadUri = imageDownloadUri;
    }

    public String getimageType() {
        return imageType;
    }

    public void setimageType(String imageType) {
        this.imageType = imageType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}