package org.jenkinsci.plugins.youtrack.youtrackapi;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a group.
 */
public class Group {
    private String name;
    private String url;

    public Group(String groupName, String groupUrl) {
        this.name = groupName;
        this.url = groupUrl;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public static class GroupListHandler extends DefaultHandler {
        private List<Group> groups;

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            groups = new ArrayList<Group>();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if (qName.equals("userGroup")) {
                String groupName = attributes.getValue("name");
                String groupUrl = attributes.getValue("url");
                Group group = new Group(groupName, groupUrl);
                groups.add(group);
            }
        }

        public List<Group> getGroups() {
            return groups;
        }
    }
}
